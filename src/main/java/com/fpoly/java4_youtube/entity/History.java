package com.fpoly.java4_youtube.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class History {
	// Vì cái history nhúng nguyên video vào nên nó sẽ gộp thực thể vào luôn
	// nên là tui viết khai báo 2 thực thể là User và Video luôn thay vì UserID hoặc
	// VideoID nha

	@Id
	@Column(name = "history")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
//	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoId", referencedColumnName = "id")
//	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})

	private Video video;

	@Column(name = "viewedDate")
	@CreationTimestamp
	// cái annotation @CreationTimestamp tại vì khi mình thêm một đối tượng mới thì
	// nó sẽ lấy getDate ngay thời điểm tạo
	private Timestamp viewedDate;

	@Column(name = "isLiked")
	private Boolean isLiked;

	@Column(name = "likedDate")
	private Timestamp likedDate;
}
