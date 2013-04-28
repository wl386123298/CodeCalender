package com.codeCalender.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	private static int iday ;
	private static List<Map<String, Object>> specials = null;
	private static List<Map<String, String>> activities =null;
	private static String[] weeks = {"��","һ","��","��","��","��","��"};
	private static String[] directions ={"����","������","����","���Ϸ�","�Ϸ�","���Ϸ�","����","������"};
	private static String[] tools  ={"Eclipseд����", "MSOfficeд�ĵ�", "���±�д����", "Windows8", "Linux",
									"MacOS", "IE", "Android�豸", "iOS�豸"};
	private static String[] varNames ={"jieguo", "huodong", "pay", "expire", "zhangdan", "every", "free", "i1", "a",
									"virtual", "ad", "spider", "mima", "pass", "ui"};
	private static String[] drinks = {"ˮ","��","���","�̲�","����","�̲�","����","ţ��","����","��֭"
									,"��ζ��ˮ","�մ�ˮ","�˶�����","����","��"};
	public static void main(String[] args) {
		Calendar calendar   = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		iday = year*10000+month*100+day;
		init();
		pickTodaysLuck();
	}
	
	private static void init(){
		specials = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", 20130221);
		map.put("type", "good");
		map.put("name", "������ϰ");
		map.put("description", "��һ�ĸ����Ӷ��ź˵���������");
		specials.add(map);
		
		activities = new ArrayList<Map<String,String>>();
		
		String[] name ={"д��Ԫ����","ϴ��","����һ������","����","��������","�ع�","ʹ��%t",
				"����","����","����","�ύ��ְ����","�����н","���ϼӰ�","��������ǰ��ţ",
				"ߣ��","���������վ","��������\"%v\"","д����%l�еķ���","�ύ����","���븴��",
				"����","��dota","��������","�޸�BUG","�������","��������","��΢��","��ABվ"};
		
		String[] good = {"д��Ԫ���Խ����ٳ���","�㼸��ûϴ���ˣ�","","������������������˼ά����",
				"������������ǰ�ȫ��","���������õ����","�㿴��������Ʒλ","�÷���ʱ�ͷ���",
				"������ǧ����Ŀ����Դ������","���Թٽ�������ܺ�","��˾�ҵ���һ��������ܸɸ����˵ļһ�Ͳ�����Ͽ����",
				"�ϰ��������ܺ�","�����ǳ���Ա������õ�ʱ��","�����㰫��������","���⻺�������",
				"��ʰ�����������","","��Ĵ�����֯�ĺܺã���һ��û��ϵ","������ͻ�ļ�������͵�","������Ҫ����ļ��ʴ������",
				"д����֮�����һ�´������潡��","�㽫��������","�����ǳ���Ա������õ�ʱ��","������BUG�����������",
				"���������齫���ͷ�Է籩","","���췢����BUG����������ͷ�Է籩","","���췢�����²��ܴ��","����Ҫ������"};
		String[] bad = {"д��Ԫ���Իή����Ŀ���Ч��", "�����Ʒ�������ϴ��","����û���Ķ��٣��Ե�ȴ����","�������ˣ��������û��ϵ",
				"���ܵ��������Ժ��","����п��ܻ�������̶","���˻��������װ��","���ڵ�ǰ�ľ������ƣ������һ�ݹ���δ�ر�����ǿ","��ֻ���е�һ�����췹�Ե�����",
				"���Թٲ�ˬ�����������","���ڵ�ǰ�ľ������ƣ������һ�ݹ���δ�ر�����ǿ","��˾���ڿ��ǲ�Ա","�����ǳ���Ա������õ�ʱ��","�ᱻʶ��",
				"Сߣ���飬��ߣ����ǿߣ�ҷ�����","���������","","��Ĵ��뽫���Ҳ��������Լ���������","��������һ��ѳ�ͻ����������Լ��ǲ���ʱ�䴩Խ��",
				"��ʲô���ⶼ���ֲ��ˣ��װ��˷�ʱ��","��ᱻ��ʺ���ӱ��ڹ�","��ᱻŰ�ĺܲ�","������Ѿ���ƣ������","�²�����BUG�����޸��ĸ���",
				"���˽�ƣ�������������ô����","","�ᱻ�ϰ忴��","�ᱻ�ϰ忴��"};
		
		for (int i = 0; i < name.length; i++) {
			Map<String, String> actMap = new HashMap<String, String>();
			actMap.put("name", name[i]);
			actMap.put("good", good[i]);
			actMap.put("bad", bad[i]);
			activities.add(actMap);
		}
	}
	
	private static int random(int dayseed, int indexseed){
		int n = dayseed %11117;
		for (int i = 0; i < 100 +indexseed; i++) {
			n = n*n;
			n = n%11117;
		}
		return n ;
	}
	
	private static void pickTodaysLuck(){
		int numGod = random(iday, 98)%3 +2;
		int numBad = random(iday,87)%3 +2;
		List<Map<String, String>> eventArr = pickRandomActivity(numGod+numBad);
		int[] specialSize = pickSpecials();
		
		for (int i = 0; i < numGod; i++) {
			System.out.println(eventArr.get(i));
		}
		
		for (int i = 0; i < numBad; i++) {
			System.out.println(eventArr.get(i));
		}
	}
	

	private static List<Map<String,String>> pickRandomActivity(int size) {
		List<Map<String, String>> picked_events  = pickRandom(activities, size);
		for (Map<String, String> pickRes : picked_events) {
			pickRes = parse(pickRes);
		}
		return picked_events;
	}
	
	private static Map<String, String> parse(Map<String, String> pickRes) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", pickRes.get("name"));
		result.put("good", pickRes.get("good"));
		result.put("bad", pickRes.get("bad"));
		String rName = result.get("name");
		if (rName.indexOf("%v")!=-1) {
			rName = rName.replace("%v",varNames[random(iday, 12)%varNames.length]);
		}
		
		if (rName.indexOf("%t")!=-1) {
			rName = rName.replace("%t", tools[random(iday, 11) % tools.length]);
		}
		
		if (rName.indexOf("%l")!=-1) {
			rName = rName.replace("%l",String.valueOf((random(iday, 12)%247+30)));
		}
		
		return result;
	}

	private static List<Map<String, String>> pickRandom(List<Map<String, String>> array,int size){
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for (Map<String, String> map : array) {
			result.add(map);
		}
		
		for(int j =0;j<array.size()-size;j++){
			int index = random(iday, j)%result.size();
			result.remove(index);
		}
		return result;
	}

	private static int[] pickSpecials(){
		int[] specialSize ={0,0};
		for (Map<String, Object> special : specials) {
			if (iday == (Integer)special.get("date")) {
				if (special.get("type").equals("good")) {
					specialSize[0]++;
					System.out.println("name:"+special.get("name")+"good:"+special.get("type"));
				}else {
					System.out.println("name:"+special.get("name")+"bad:"+special.get("type"));
				}
			}
		}
		return specialSize;
	}
}
