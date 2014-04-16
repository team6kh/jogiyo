package common;

import java.util.Random;

// Random Number Generator
public class RNG
{    
    // 랜덤숫자 4자리를 생성합니다.
    public String sEv_code()
    {        
        Random random = new Random();
        int iRng = random.nextInt(10000);
        String sEv_code = Integer.toString(iRng);
        return sEv_code;        
    }    
}
