package web.day02.jacob;

import com.jacob.activeX.ActiveXComponent;

public class TestJacob {
    public static void main(String[] args) {
        ActiveXComponent app = new ActiveXComponent("Word.Application"); 
        System.out.println("Word启用成功");
	}
}
