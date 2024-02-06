package com.ezen.go90.domain.article.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ImageForm {
	private String uploader;
	private String description;
	private MultipartFile uploadfile; // 단일파일
	private List<MultipartFile> uploadfiles; // 다중파일
}
