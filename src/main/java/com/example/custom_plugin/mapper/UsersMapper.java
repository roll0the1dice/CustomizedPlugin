package com.example.custom_plugin.mapper;

import com.example.custom_plugin.model.Users;
import com.example.custom_plugin.model.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    int insert(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    int insertSelective(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    int updateByExampleSelective(@Param("row") Users row, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users
     *
     * @mbg.generated Sun Feb 09 19:19:57 CST 2025
     */
    int updateByExample(@Param("row") Users row, @Param("example") UsersExample example);
}