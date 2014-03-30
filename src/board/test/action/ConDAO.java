package board.test.action;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ConDAO {
	
	public SqlMapClient getCon() throws IOException{
		Reader reader = Resources.getResourceAsReader("sqlMapConfig.xml"); // sqlMapConfig.xml 파일의 설정내용을 가져온다.
		SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);	// sqlMapConfig.xml의 SQL맵만 다시 읽어들임 = DAO
		reader.close();
		return sqlMapper;
	}

}
