package com.useful_person.okrm.user_service.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Data
@Table(name = "t_user_info")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("用户模型")
public class UserInfo {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Getter
    @Setter
    @ToString.Include(rank = 0)
    @ApiModelProperty(value = "唯一标识，自动获取")
    private UUID uuid;

    @Getter
    @Setter
    @ToString.Include(rank = 1)
    @NotNull(message = "用户名不能为空")
    @Length(min = 6, max = 12)
    @ApiModelProperty(value = "用户名")
    private String username;

    @Getter
    @Setter
    @ToString.Include(rank = 2)
    @NotNull(message = "用户昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @Getter
    @Setter
    @ToString.Include(rank = 3)
    @Email(message = "Email格式不正确")
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @Getter
    @Setter
    private String createAt;

    @Getter
    @Setter
    private String updateAt;

    @Getter
    @Setter
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间自动获取")
    private Timestamp createdTime;

    @Getter
    @Setter
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间，自动获取")
    private Timestamp updatedTime;

}
