package com.bc.dto;

public class BusinessCards
{
    private static int serial;
    private int num;
    private String name;
    private String tel;
    private String email;
    
    
   
    


    //아하. 현재, 프로그램 종료할 때마다, static 값이 2로 초기화되서 그런가봄.
    // 추측하기론, 한 창에서 여러번 입력을 한다면, 값을 계속 입력할 수 있을 것임.
    
//   생성자에 serial값을 늘게 만드니, insert할 때 외에도 값이 마구 증가함
//    insert할 때만 올라가도록 해야겠음
    
    public BusinessCards()
    { 
//        this.num= ++serial;
    }

    public BusinessCards(String name, String tel, String email)
    {
//        this.num= ++serial;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }
    
    public static int getSerial()
    {
        return ++serial;
    }

    public static void setSerial(int serial)
    {
        BusinessCards.serial = serial;
    }

    public void setNum(int num)
    {
        this.num = num;
    }    
    
    public int getNum()
    {
        return num;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getTel()
    {
        return tel;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    
    @Override
    public String toString()
    {
        return "번호: " + num + ", 이름: " + name + ", 전화번호: " + tel
                + ", 이메일 : " + email;
    }
    
    
    
}
