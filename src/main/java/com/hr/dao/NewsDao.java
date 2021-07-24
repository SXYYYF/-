package com.hr.dao;

import com.hr.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.nio.channels.SelectableChannel;
import java.util.List;

@Repository
public interface NewsDao extends PagingAndSortingRepository<News, Long> {

    /*
    *查询所有新闻，根据创建时间倒序排列
    * @return
    * */
     List<News> getAllByOrderByNcreateDateDesc();


    @Query(value = "select * from news where ntid = (select tid from topic where tname =?1) order by ncreateDate desc ",nativeQuery = true)
     List<News> getAllByName(@Param("tname") String tname);


     List<News> getAllByNtidOrderByNcreateDateDesc(Long ntid);

     News getNewsByNid(Long nid);
     //查询所有新闻的条数
     int countAllBy();

     @Query(value = "select * from news order by ncreateDate desc limit ?1,?2",nativeQuery = true)
    List<News> getNewsByPage(@Param("index") int index,@Param("pageSize") int pageSize);

     boolean existsNewsByNtid(Long ntid);


    @Override
    void deleteById(Long nid);
}
