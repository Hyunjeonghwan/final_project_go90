package com.ezen.go90.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @Project : final_project_go90
 * @author 현정환
 * @Date : 2023. 9. 8.
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Image {

	int imgId;
	String imgContent;
	int articleId;
	String imgPath;
	
}
