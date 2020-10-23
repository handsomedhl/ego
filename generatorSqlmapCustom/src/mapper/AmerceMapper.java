package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.Amerce;
import pojo.AmerceExample;

public interface AmerceMapper {
    int countByExample(AmerceExample example);

    int deleteByExample(AmerceExample example);

    int deleteByPrimaryKey(Integer amerceId);

    int insert(Amerce record);

    int insertSelective(Amerce record);

    List<Amerce> selectByExample(AmerceExample example);

    Amerce selectByPrimaryKey(Integer amerceId);

    int updateByExampleSelective(@Param("record") Amerce record, @Param("example") AmerceExample example);

    int updateByExample(@Param("record") Amerce record, @Param("example") AmerceExample example);

    int updateByPrimaryKeySelective(Amerce record);

    int updateByPrimaryKey(Amerce record);
}