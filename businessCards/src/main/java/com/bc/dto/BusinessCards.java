package com.bc.dto;

public class BusinessCards
{
    private static int serial=1;
    private int num;
    private String name;
    private String tel;
    private String email;
    
    
   
    
    public BusinessCards()
    {
        super();
        this.num= serial++;
    }

    public BusinessCards(String name, String tel, String email)
    {
        super();
        this.num= serial++;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }
    
    public int getNum()
    {
        return num;
    }
    public void setNum(int num)
    {
        this.num = num;
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
