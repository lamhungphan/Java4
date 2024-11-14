package com.fpoly.java4_youtube.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history")
public class History {
	//    Vì History nhúng nguyên video vào nên nó sẽ gộp thực thể vào luôn
	// => khai báo 2 thực thể là User và Video thay vì UserID & VideoID
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Video video;

	@Column(name = "viewedDate")
	@CreationTimestamp
	private Timestamp viewedDate;

	@Column(name = "isLiked")
	private Boolean isLiked;

	@Column(name = "likedDate")
	private Timestamp likedDate;
}
