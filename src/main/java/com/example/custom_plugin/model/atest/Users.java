package com.example.custom_plugin.model.atest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import com.example.custom_plugin.service.SnowflakeIdGenerator;

@Data
@Entity
@DynamicInsert
public class Users {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.id
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    @Id
    @GeneratedValue 
    @SnowflakeIdGenerator(workerId=1, datacenterId=1)
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.username
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.password
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.email
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.avatar
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.created_at
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.updated_at
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private Date updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.is_deleted
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private Byte isDeleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ATest..users.role
     *
     * @mbg.generated Sat Feb 15 17:09:43 CST 2025
     */
    private Byte role;
}