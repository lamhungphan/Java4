package com.fpoly.java4_youtube.entity;

import com.fpoly.java4_youtube.constant.NamedStored;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = NamedStored.FIND_USERS_LIKED_VIDEO_BY_VIDEO_HREF,
                procedureName = "sp_selectUsersLikedVideoByVideoHref",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = "videoHref", type = String.class)) // nên tạo constant lưu video href
})
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "isAdmin")
    private Boolean isAdmin;

    @Column(name = "isActive")
    private Boolean isActive;
}