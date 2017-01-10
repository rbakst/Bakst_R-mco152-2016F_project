package shul;

public enum USState 
{
	ALABAMA("AL"),
	ALASKA("AK"),
	ARIZONA("AZ"),
	ARKANSAS("AR"),
	CALIFORNIA("CA"),
	COLORADO("CO"),
	CONNECTICUT("CT"),
	DELAWARE("DE"),
	FLORIDA("FL"),
	GEORGIA("GA"),
	HAWAII("HI"),
	IDAHO("ID"),
	ILLINOIS("IL"),
	INDIANA("IN"),
	IOWA("IA"),
	KANSAS("KS"),
	KENTUCKY("KY"),
	LOUISIANA("LA"),
	MAINE("ME"),
	MARYLAND("MD"),
	MASSACHUSETTS("MA"),
	MICHIGAN("MI"),
	MINNESOTA("MN"),
	MISSISSIPPI("MS"),
	MISSOURI("MO"),
	MONTANA("MT"),
	NEBRASKA("NE"),
	NEVADA("NV"),
	NEWHAMPSHIRE("NH"),
	NEWJERSEY("NJ"),
	NEWMEXICO("NM"),
	NEWYORK("NY"),
	NORTHCAROLINA("NC"),
	NORTHDAKOTA("ND"),
	OHIO("OH"),
	OKLAHOMA("OK"),
	OREGON("OR"),
	PENNSYLVANIA("PA"),
	RHODEISLAND("RI"),
	SOUTHCAROLINA("SC"),
	SOUTHDAKOTA("SD"),
	TENNESSEE("TN"),
	TEXAS("TX"),
	UTAH("UT"),
	VIRGINIA("VA"),
	VERMONT("VT"),
	WASHINGTON("WA"),
	WESTVIRGINIA("WV"),
	WISCONSIN("WI"),
	WYOMING("WY");
   
	
	private String symbol;
	
	
	
	private USState ( String symbol){
		
	     this.symbol = symbol;
	}
	
	
	
	public String getSymbol(){
		return symbol;
	}
	
	
	   public static USState get(String code){
	       switch(code) {
	       case "AL": return ALABAMA;
	        case "AK": return ALASKA;
	        case "Z": return ARIZONA;
	        case "AR": return ARKANSAS;
	        case "CA": return CALIFORNIA;
	        case "CO": return COLORADO;
	        case "CT": return CONNECTICUT;
	        case "DE": return DELAWARE;
	        case "GA": return GEORGIA;
	        case "HI": return HAWAII;
	        case "ID": return IDAHO;
	        case "IL": return ILLINOIS;
	        case "IN": return INDIANA;
	        case "IA": return IOWA;
	        case "KS": return KANSAS;
	        case "KY": return KENTUCKY;
	        case "LA": return LOUISIANA;
	        case "ME": return MAINE;
	        case "MD": return MARYLAND;
	        case "MA": return MASSACHUSETTS;
	        case "MI": return MICHIGAN;
	        case "MN": return MINNESOTA;
	        case "MS": return MISSISSIPPI;
	        case "MO": return MISSOURI;
	        case "MT": return MONTANA;
	        case "NE": return NEBRASKA;
	        case "NV": return NEVADA;
	    	case "NH": return NEWHAMPSHIRE;
	    	case "NJ": return NEWJERSEY;
	    	case "NM": return NEWMEXICO;
	    	case "NY": return NEWYORK;
	    	case "NC": return NORTHCAROLINA;		
	    	case "ND": return NORTHDAKOTA;
	    	case "OH": return OHIO;
	    	case "OK": return OKLAHOMA;
	    	case "OR": return OREGON;
	    	case "PA": return PENNSYLVANIA;
	    	case "RI": return RHODEISLAND;
	    	case "SC": return SOUTHCAROLINA;
	    	case "SD": return SOUTHDAKOTA;
	    	case "TN": return TENNESSEE;
	    	case "TX": return TEXAS;
	    	case "UT": return UTAH;
	    	case "VA": return VIRGINIA;
	    	case "VT": return VERMONT;
	    	case "WA": return WASHINGTON;
	    	case "WV": return WESTVIRGINIA;
	    	case "WI": return WISCONSIN;
	    	case "WY": return WYOMING;
	        }
	       
	      return null;
	   }

}
