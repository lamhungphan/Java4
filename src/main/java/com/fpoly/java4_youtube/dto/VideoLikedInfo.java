package com.fpoly.java4_youtube.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoLikedInfo {
    private Integer videoId;
    private String title;
    private String href;
    private Integer totalLike;
}
