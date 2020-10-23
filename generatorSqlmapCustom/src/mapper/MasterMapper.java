package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.Master;
import pojo.MasterExample;

public interface MasterMapper {
    int countByExample(MasterExample example);

    int deleteByExample(MasterExample example);

    int deleteByPrimaryKey(String sysId);

    int insert(Master record);

    int insertSelective(Master record);

    List<Master> selectByExample(MasterExample example);

    Master selectByPrimaryKey(String sysId);

    int updateByExampleSelective(@Param("record") Master record, @Param("example") MasterExample example);

    int updateByExample(@Param("record") Master record, @Param("example") MasterExample example);

    int updateByPrimaryKeySelective(Master record);

    int updateByPrimaryKey(Master record);
}