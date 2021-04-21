package com.test.dao;

import com.test.domain.User;
import org.aspectj.apache.bcel.classfile.Module;
import org.hibernate.criterion.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper extends JpaRepository<User,String>, CrudRepository<User,String> {

    //?1 ?2  &   :id :name

//    List<User>  getAllByName(String name);

//    @Query(value = "select t.* from table_1 t where COLUMN_2 = ?1 limit ?2,?3" , nativeQuery = true)
//    List<User> queryName(String value, Integer offset , Integer pageSize);
//
//    @Query(value = "select t.* from table_1 t where COLUMN_8 = ?1 limit ?2,?3" , nativeQuery = true)
//    List<User> queryPhoneNumber(String value,Integer offset ,Integer pageSize);
//
//    @Query(value = "select t.* from table_1 t where COLUMN_6 = ?1 limit ?2,?3" , nativeQuery = true)
//    List<User> queryIdNumber(String value,Integer offset ,Integer pageSize);

//    @Query(value = "select count(COLUMN_2) from table_1 where COLUMN_2 = :name " , nativeQuery = true)
//    int countByName(String name);

//    @Query(value = "select count(COLUMN_8) from table_1 where COLUMN_8 = :name " , nativeQuery = true)
//    int countByPhoneNumber(String name);

//    @Query(value = "select count(COLUMN_6) from table_1 where COLUMN_6 = :name " , nativeQuery = true)
//    int countByIdNumber(String name);

//    @Query(value = "select * from table_1  where COLUMN_8 = :number ",
//            countQuery = "select count(COLUMN_8) from table_1 t where COLUMN_8 = :number",
//            nativeQuery = true)
//    Page<User> findByPhoneNumber(String number, Pageable pageable);


//    @Query(value = "select count(COLUMN_8) from table_1 t" , nativeQuery = true)
//    List<User> findAllByPhoneNumber (String name);

//    List<User> findByName(String name);
//
//    List<User> findByIdNumber(String idNumber);

//    @Modifying
//    @Query("update User u set u.name = :user")
//    int updateById(User user);
//
//    @Modifying
//    @Query("update User u set u.name = ?1 where u.id = ?2")
//    int modifyByIdAndUserId(String  userName, Long id);
//
//    @Transactional
//    @Modifying
//    @Query("delete from User where Column_1 =:id")
//    void deleteByUserId(String id);

}
