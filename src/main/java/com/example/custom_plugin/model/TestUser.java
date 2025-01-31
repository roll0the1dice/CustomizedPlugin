package com.example.custom_plugin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@DynamicInsert
public class TestUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.id
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.username
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.useraccount
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String useraccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.avatarurl
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String avatarurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.gender
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.userpassword
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String userpassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.phone
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.email
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.user_state
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Integer userState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.createTime
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.updateTime
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.isDelete
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Byte isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test_user.userstate
     *
     * @mbg.generated Sun Jan 26 19:34:13 CST 2025
     */
    private Integer userstate;
}