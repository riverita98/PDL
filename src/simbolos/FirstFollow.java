package simbolos;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFollow {

	//////////////////////// FIRST //////////////////////////////
	///SIN DEPENDECIAS
	public static ArrayList<String> FIRST_T = new ArrayList<String>(Arrays.asList("int", "bool","string"));
	public static ArrayList<String> FIRST_L = new ArrayList<String>(Arrays.asList("id"));
	public static ArrayList<String> FIRST_X = new ArrayList<String>(Arrays.asList(",", "lam", "="));
	public static ArrayList<String> FIRST_Y = new ArrayList<String>(Arrays.asList("lam", ","));
	public static ArrayList<String> FIRST_Z = new ArrayList<String>(Arrays.asList("lam","else"));
	public static ArrayList<String> FIRST_S = new ArrayList<String>(Arrays.asList("print","prompt","return","id"));
	public static ArrayList<String> FIRST_I = new ArrayList<String>(Arrays.asList(",","lam"));
	public static ArrayList<String> FIRST_D = new ArrayList<String>(Arrays.asList("=","+=","-=","|="));
	public static ArrayList<String> FIRST_F = new ArrayList<String>(Arrays.asList("function"));
	public static ArrayList<String> FIRST_N = new ArrayList<String>(Arrays.asList(",", "lam"));
	public static ArrayList<String> FIRST_Ep = new ArrayList<String>(Arrays.asList("||","lam"));
	public static ArrayList<String> FIRST_Op = new ArrayList<String>(Arrays.asList("&&","lam"));
	public static ArrayList<String> FIRST_Ppp = new ArrayList<String>(Arrays.asList("==","!=","lam"));
	public static ArrayList<String> FIRST_Qp = new ArrayList<String>(Arrays.asList(">","<","lam"));
	public static ArrayList<String> FIRST_Rp = new ArrayList<String>(Arrays.asList("+","-","lam"));
	public static ArrayList<String> FIRST_Spp = new ArrayList<String>(Arrays.asList("*","/","lam"));
	public static ArrayList<String> FIRST_Up = new ArrayList<String>(Arrays.asList("++","--","lam"));
	public static ArrayList<String> FIRST_V = new ArrayList<String>(Arrays.asList("(","id","cte_ent","cte_cad"));
	public static ArrayList<String> FIRST_Vp = new ArrayList<String>(Arrays.asList("(","lam"));
	
	////CON DEPENDENCIAS
	public static ArrayList<String> FIRST_U = new ArrayList<String>();
	public static ArrayList<String> FIRST_Sp = new ArrayList<String>();
	public static ArrayList<String> FIRST_R = new ArrayList<String>();
	public static ArrayList<String> FIRST_Q = new ArrayList<String>();
	public static ArrayList<String> FIRST_Pp = new ArrayList<String>();
	public static ArrayList<String> FIRST_O = new ArrayList<String>();
	public static ArrayList<String> FIRST_E = new ArrayList<String>();
	public static ArrayList<String> FIRST_C = new ArrayList<String>();
	public static ArrayList<String> FIRST_M = new ArrayList<String>();
	public static ArrayList<String> FIRST_K = new ArrayList<String>();
	public static ArrayList<String> FIRST_J = new ArrayList<String>();
	public static ArrayList<String> FIRST_H = new ArrayList<String>();
	public static ArrayList<String> FIRST_G = new ArrayList<String>();
	public static ArrayList<String> FIRST_A = new ArrayList<String>();
	public static ArrayList<String> FIRST_Xp = new ArrayList<String>();
	public static ArrayList<String> FIRST_B = new ArrayList<String>();
	public static ArrayList<String> FIRST_P = new ArrayList<String>();
	
	//////////////////////// FOLLOW //////////////////////////////
	///SIN DEPENDECIAS
	public static ArrayList<String> FOLLOW_P = new ArrayList<String>(Arrays.asList("eof"));
	public static ArrayList<String> FOLLOW_X = new ArrayList<String>(Arrays.asList(";"));
	public static ArrayList<String> FOLLOW_Y = new ArrayList<String>(Arrays.asList(";"));
	public static ArrayList<String> FOLLOW_A = new ArrayList<String>(Arrays.asList(")"));
	public static ArrayList<String> FOLLOW_I = new ArrayList<String>(Arrays.asList(")"));
	public static ArrayList<String> FOLLOW_G = new ArrayList<String>(Arrays.asList(";"));
	public static ArrayList<String> FOLLOW_J = new ArrayList<String>(Arrays.asList(")"));
	public static ArrayList<String> FOLLOW_K = new ArrayList<String>(Arrays.asList("id"));
	public static ArrayList<String> FOLLOW_M = new ArrayList<String>(Arrays.asList(")"));
	public static ArrayList<String> FOLLOW_C = new ArrayList<String>(Arrays.asList("}"));
	
	
	
	////CON DEPENDENCIAS
	public static ArrayList<String> FOLLOW_T = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_L = new ArrayList<String>();	
	public static ArrayList<String> FOLLOW_Z = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_S = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_D = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_F = new ArrayList<String>();	
	public static ArrayList<String> FOLLOW_N = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Ep = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Op = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Ppp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Qp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Rp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Spp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Up = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_V = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Vp = new ArrayList<String>();	
	public static ArrayList<String> FOLLOW_U = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Sp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_R = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Q = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_Pp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_O = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_E = new ArrayList<String>();	
	public static ArrayList<String> FOLLOW_H = new ArrayList<String>();	
	public static ArrayList<String> FOLLOW_Xp = new ArrayList<String>();
	public static ArrayList<String> FOLLOW_B = new ArrayList<String>();
	
	public static void init() {		
		
		add(FIRST_U,"+");
		add(FIRST_U,"-");
		add(FIRST_U,"!");
		add(FIRST_U,FIRST_V);
		
		add(FIRST_Sp,FIRST_U);
		add(FIRST_R,FIRST_Sp);
		add(FIRST_Q,FIRST_R);
		add(FIRST_Pp,FIRST_Q);
		add(FIRST_O,FIRST_Pp);
		add(FIRST_E,FIRST_O);		
		
		add(FIRST_M,FIRST_T);
		add(FIRST_M,"lam");
		
		add(FIRST_K,FIRST_T);
		add(FIRST_K,"lam");
		
		add(FIRST_J,FIRST_E);
		add(FIRST_J,"lam");
		
		add(FIRST_H,FIRST_D);
		add(FIRST_H,"(");
		
		add(FIRST_G,FIRST_E);
		add(FIRST_G,"lam");
		
		add(FIRST_A,FIRST_E);
		
		add(FIRST_Xp,FIRST_S);
		add(FIRST_Xp, "{");
		
		add(FIRST_B,"var");
		add(FIRST_B,"if");
		add(FIRST_B,"do");
		add(FIRST_B,"while");
		add(FIRST_B, FIRST_S);
		
		add(FIRST_C,FIRST_B);
		add(FIRST_C,"lam");
		
		add(FIRST_P,FIRST_B);
		add(FIRST_P,FIRST_F);
		
		
		////FOLLOW////
		add(FOLLOW_B, FIRST_P);
		add(FOLLOW_B, FIRST_C);
		add(FOLLOW_B, FOLLOW_C);
		
		add(FOLLOW_T,FIRST_L);
		add(FOLLOW_T,FOLLOW_K);
		add(FOLLOW_T,"id");
		
		add(FOLLOW_L,FOLLOW_B);
		add(FOLLOW_L,FOLLOW_X);
		add(FOLLOW_L,FOLLOW_Y);
		//add(FOLLOW_L,")"); CAMBIO Vp -> (L) por Vp-> (J)
		
		add(FOLLOW_Xp,FOLLOW_B);
		
		add(FOLLOW_Z, FOLLOW_Xp);
		
		add(FOLLOW_S,FOLLOW_B);
		
		add(FOLLOW_H, FOLLOW_S);
		
		add(FOLLOW_D, FIRST_E);
		
		add(FOLLOW_F,FIRST_P);
		
		add(FOLLOW_N,FOLLOW_M);
		
		add(FOLLOW_E, ")");
		add(FOLLOW_E, FOLLOW_B);
		add(FOLLOW_E, FIRST_Y);
		add(FOLLOW_E, FOLLOW_Y);
		add(FOLLOW_E, FIRST_I);
		add(FOLLOW_E, FOLLOW_I);
		add(FOLLOW_E, FOLLOW_G);
		add(FOLLOW_E, FOLLOW_H);
		add(FOLLOW_E, ";");
		
		add(FOLLOW_Ep,FOLLOW_E);
		
		add(FOLLOW_O,FIRST_Ep);
		add(FOLLOW_O,FOLLOW_Ep);
		
		add(FOLLOW_Op,FOLLOW_O);
		
		add(FOLLOW_Pp,FIRST_Op);
		add(FOLLOW_Pp,FOLLOW_Op);
		
		add(FOLLOW_Ppp,FOLLOW_Pp);
		
		add(FOLLOW_Q,FIRST_Ppp);
		add(FOLLOW_Q,FOLLOW_Ppp);
		
		add(FOLLOW_Qp,FOLLOW_Q);
		
		add(FOLLOW_R,FIRST_Qp);
		add(FOLLOW_R,FOLLOW_Qp);
		
		add(FOLLOW_Rp,FOLLOW_R);
		
		add(FOLLOW_Sp,FIRST_Rp);
		add(FOLLOW_Sp,FOLLOW_Rp);
		
		add(FOLLOW_Spp, FOLLOW_Sp);
		
		add(FOLLOW_U,FIRST_Spp);
		add(FOLLOW_U,FOLLOW_Spp);
		
		add(FOLLOW_Up,FOLLOW_U);
		
		add(FOLLOW_V,FIRST_Up);
		add(FOLLOW_V,FOLLOW_Up);
		
		add(FOLLOW_Vp, FOLLOW_V);		
	}
	
	
	private static void add(ArrayList<String> des, ArrayList<String> sou) {
		des.addAll(sou);
	}
	
	private static void add(ArrayList<String> des, String sou) {
		des.add(sou);
	}
;
	


	
	
}
