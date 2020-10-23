package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.Books;
import pojo.BooksExample;

public interface BooksMapper {
    int countByExample(BooksExample example);

    int deleteByExample(BooksExample example);

    int deleteByPrimaryKey(String bookId);

    int insert(Books record);

    int insertSelective(Books record);

    List<Books> selectByExample(BooksExample example);

    Books selectByPrimaryKey(String bookId);

    int updateByExampleSelective(@Param("record") Books record, @Param("example") BooksExample example);

    int updateByExample(@Param("record") Books record, @Param("example") BooksExample example);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);
}