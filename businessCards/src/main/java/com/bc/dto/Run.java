package com.bc.dto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Run
{
    public static final Scanner SC = new Scanner(System.in);
    private int order;
    private String answer;
    
    BcDAO dao= BcDAO.getDao();
    
    
    public int getOrder()
    {
        return order;
    }
    
    public void setOrder(int order)
    {
        this.order = order;
    }
    
    
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    
    

    public void greeting()
    {
        System.out.println("반갑습니다.");
        System.out.println("1:명함입력  2:전체명함보기  3:명함검색  4:명함수정  5:명함삭제 6:프로그램 종료");
        
        do
        {
            try
            {
                order = SC.nextInt();
                if(order>=1 && order<=6)
                {
                    break;
                }
            }
            catch (InputMismatchException e)
            {
                e.printStackTrace();
            }    
            System.out.println("1에서 6 사이의 숫자를 입력해주세요");
            SC.nextLine();
        }
        while(true);
    }
    
    
    public void insert()
    {
        System.out.println("----------명함입력----------");
        
        BusinessCards bc= new BusinessCards();
        
        System.out.println("이름: ");
        bc.setName(SC.next());
        System.out.println("전화번호: ");
        bc.setTel(SC.next());
        System.out.println("이메일: ");
        bc.setEmail(SC.next());
        
        dao.addCard(bc);
        
    }
    
    
    public void delete()
    {
        System.out.println("----------명함삭제----------");
        
        System.out.println("어떤 명함을 삭제하시겠습니까?");
        

        
        //검색.
        //이름이 2개 이상일 땐?
    }
    
    public void search()
    {
        searchCard: while(true)
        {
            System.out.println("검색합니다. 이름을 입력해주세요");
            String name = SC.next();
            List<BusinessCards> arr= dao.searchCard(name);
            
            if(arr.isEmpty())
            {
                System.out.println("검색 결과가 없습니다.");
                System.out.println("다시 검색하시겠습니까? y/n");
              
                answer= SC.next();
                  if(answer.equals("y")) 
                  {
                      continue searchCard;   
                  }   
                  else
                  {
                      return;
                  }

//              do
//                {
//                    try
//                    {
//                        String answer= SC.next();
//                        if(answer.equals("y")) 
//                        {
//                            continue searchCard;   
//                        }   
//                        else
//                        {
//                            return;
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                    System.out.println("y 혹은  n을 입력해주세요.");
//                    SC.nextLine();
//                    
//                }
//                while(true);
            }
            else
            {    
                System.out.println(arr.toString());
                return;
                
            }
        }
        
        
        
    }
    
    
    public static void main(String[] args)
    {
        Run run = new Run();
        
        
        run.greeting();   
        
        switch(run.getOrder())
        {
            case 1: run.insert();
            break;
            case 3: run.search();
                break;
            case 4: run.delete();
            break;
        }
        
    }
    
    
    
}

