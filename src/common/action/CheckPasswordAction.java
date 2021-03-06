package common.action;

import user.buyer.dto.BuyerDTO;
import user.seller.dto.SellerDTO;
import board.test.dto.TestDTO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;

import common.ConDAOAware;

public class CheckPasswordAction implements Action, ConDAOAware
{
    private SqlMapClient sqlMapper;

    private String modalParam; // modal에서 가져오는 파라미터 : 타겟 액션 이름
    private String modalParam_key;
    private String modalParam_pw;

    public void setConDAO(SqlMapClient sqlMapper)
    {
        this.sqlMapper = sqlMapper;
    }

    // 테스트 게시판 글 비밀번호 체크 액션
    public String execute() throws Exception
    {
        System.out.println("CheckPasswordAction execute()");
        
        // 테스트 게시판 글 수정/삭제 요청
        if (getModalParam().equals("updateTestForm") || getModalParam().equals("deleteTest"))
        {
            // 객체 생성
            TestDTO testDTO = new TestDTO();

            // 파라미터 설정
            testDTO.setTest_num(Integer.parseInt(getModalParam_key()));
            testDTO.setTest_writer_pw(getModalParam_pw());

            // 해당 글의 비밀번호 가져오기
            testDTO = (TestDTO) sqlMapper.queryForObject("Test.selectTestWriterPw", testDTO);

            // 입력한 비밀번호가 틀리면(없으면) ERROR 리턴
            if (testDTO != null)
            {
                return SUCCESS;
            } else
            {
                return ERROR;
            }
        // 구매자 정보 수정/삭제
        } else if (getModalParam().equals("updateBuyerForm") || getModalParam().equals("deleteBuyer"))
        {
            // 객체 생성
            BuyerDTO buyerDTO = new BuyerDTO();

            // 파라미터 설정
            buyerDTO.setBuyer_id(getModalParam_key());
            buyerDTO.setBuyer_pw(getModalParam_pw());

            // 해당 유저의 비밀번호 가져오기
            buyerDTO = (BuyerDTO) sqlMapper.queryForObject("Buyer.selectBuyerPw", buyerDTO);

            // 입력한 비밀번호가 틀리면(없으면) ERROR 리턴
            if (buyerDTO != null)
            {
                return SUCCESS;
            } else
            {
                return ERROR;
            }
        // 판매자 정보 수정/삭제
        } else if (getModalParam().equals("updateSellerForm") || getModalParam().equals("deleteSeller"))
        {
            System.out.println("getModalParam():"+getModalParam());
            
            // 객체 생성
            SellerDTO sellerDTO = new SellerDTO();
            
            // 파라미터 설정
            sellerDTO.setSeller_id(getModalParam_key());
            sellerDTO.setSeller_pw(getModalParam_pw());
            
            System.out.println("getModalParam_key():"+getModalParam_key());
            System.out.println("getModalParam_pw()():"+getModalParam_pw());
            
            // 해당 유저의 비밀번호 가져오기
            sellerDTO = (SellerDTO) sqlMapper.queryForObject("Seller.selectSellerPw", sellerDTO);
            
            // 입력한 비밀번호가 틀리면(없으면) ERROR 리턴
            if (sellerDTO != null)
            {
                System.out.println("sellerDTO not null");
                return SUCCESS;
            } else
            {
                System.out.println("sellerDTO null");
                return ERROR;
            }
        }
        return ERROR;
    }

    // getter & setter
    public String getModalParam()
    {
        return modalParam;
    }

    public void setModalParam(String modalParam)
    {
        this.modalParam = modalParam;
    }

    public String getModalParam_key()
    {
        return modalParam_key;
    }

    public void setModalParam_key(String modalParam_key)
    {
        this.modalParam_key = modalParam_key;
    }

    public String getModalParam_pw()
    {
        return modalParam_pw;
    }

    public void setModalParam_pw(String modalParam_pw)
    {
        this.modalParam_pw = modalParam_pw;
    }
    
}
