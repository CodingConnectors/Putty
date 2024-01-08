package com.nowon.cho.faq.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.nowon.cho.domain.dto.BoardSaveDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberRepository;
import com.nowon.cho.faq.entity.Board;
import com.nowon.cho.faq.repository.BoardRepository;
import com.nowon.cho.utils.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	
	//글 작성 (로컬)
	/*public void write(Board board, MultipartFile file) throws Exception{
		
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
		
		UUID uuid = UUID.randomUUID();
		
		
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		
		File saveFile = new File(projectPath, fileName);
	
		file.transferTo(saveFile);
		////////////////////////////////////////
		
		// 파일을 저장하는 경로 대신 URL을 사용하도록 수정
			String fileUrl = "/files/" + fileName;
			board.setFilename(fileName);
			board.setFilepath(fileUrl);
			boardRepository.save(board);

		
		////////////////////////////////////////
		board.setFilename(fileName);
		board.setFilepath("/files/" + fileName);
		
		boardRepository.save(board);
		
	}*/
	
	//AWS 저장
	
	@Autowired
    private AmazonS3 s3Client; // AmazonS3 인터페이스를 사용하도록 변경

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${cloud.aws.s3.upload-path}")
    private String uploadPath;
    
    //게시글 저장 로직
    public void write(Authentication authentication, BoardSaveDTO dto, MultipartFile file, Model model) {
    	String email=authentication.getName();// 로그인시 id(이메일) 식별자
    	MemberEntity member=memberRepository.findByEmail(email);//작성자 정보
    	
    	UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        // AWS S3에 파일 업로드
        PutObjectResult result=uploadFileToS3(fileName, file);
    	System.out.println(">>>>"+result);
    	
    	//toEntity에 작성자(Member객체), s3버킷객체, s3경로+버킷 setting
    	boardRepository.save(dto.toEntity(member, fileName, uploadPath+fileName));
		
	}
    
   
    	
    


    private PutObjectResult uploadFileToS3(String fileName, MultipartFile file) {
        String s3Key = uploadPath + fileName;

        ObjectMetadata metadata = new ObjectMetadata();	
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        PutObjectRequest putObjectRequest=null;
		try {
			putObjectRequest = new PutObjectRequest(bucketName, s3Key, file.getInputStream(), metadata)
			        .withCannedAcl(CannedAccessControlList.PublicRead);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//PutObjectResult result;
		return s3Client.putObject(putObjectRequest);
    }
	
    
	//리스트 메소드 생성 //게시글 리스트 처리
	public void boardList(Model model, String searchKeyword, int page,int limit) {
		//int limit=8;//페이지당 게시글수
		Pageable pageable=PageRequest.of(page-1, limit, Direction.DESC, "id");
		Page<Board> result;
		System.out.println(">>>>>>>>>>>>>:"+searchKeyword);
		if(searchKeyword!="" && searchKeyword!=null)
			result=boardRepository.findByTitleContaining(searchKeyword, pageable);
		else 
			result=boardRepository.findAll(pageable);
		
		
		model.addAttribute("list", result.stream().map(Board::toBoardDTO).collect(Collectors.toList()));
		model.addAttribute("pd", PageData.create(page, limit,  (int)result.getTotalElements(), 10));
		
	}
	
	public List<Board> boardSearchList(String SearchKeyword, Pageable pageable){
		
		return boardRepository.findByTitleContaining(SearchKeyword, pageable).getContent();
	}
	
	
	// 특정 게시글 불러오기
	public Board boardView(Integer id) {
		
		return boardRepository.findById(id).get();
		
	}
	
	// 특정 게시글 삭제
	public void boardDelete(Integer id) {
		
		boardRepository.deleteById(id);
		
	}




	//게시글 수정
	@Transactional
	public void updateBoard(Integer id, Board board, MultipartFile file, Model model) {
		 // 필요한 로직에 따라 업데이트 수행
	    Board boardTemp = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
	    boardTemp.setTitle(board.getTitle());
	    boardTemp.setContent(board.getContent());
	    
	    // 파일 업로드 관련 로직 수정
	    UUID uuid = UUID.randomUUID();
	    String fileUuid = uuid.toString();
	    String originalFileName = file.getOriginalFilename();
	    boardTemp.setFileUuid(fileUuid);
	    boardTemp.setOriginalFileName(originalFileName);

	    PutObjectResult result = uploadFileToS3(fileUuid + "_" + originalFileName, file);
	    System.out.println(">>>>" + result);

	    // 업데이트된 게시물을 저장
	    boardRepository.save(boardTemp);
		
	}

	

	
	
	//URI 파라미터 유틸 메서드
	/*
	public String makeURI(int page){
	
		UriComponents ucp = UriComponentsbuilder.newInstance().queryParam("pageNum",page)
			.queryParam("cpp", paging.getCpp())
			.queryParam("keyword", paging.getKeyword())
			.queryParam("condition", paging.getCondition())
			.build();
		return ucp.toUriString();
	}
	*/

}
