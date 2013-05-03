package org.wl.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalenderData {
	public static String[] weeks = {"��","һ","��","��","��","��","��"};
	public static String[] directions ={"����","������","����","���Ϸ�","�Ϸ�","���Ϸ�","����","������"};
	public static String[] tools  ={"Eclipseд����", "MSOfficeд�ĵ�", "���±�д����", "Windows8", "Linux",
									"MacOS", "IE", "Android�豸", "iOS�豸"};
	public static String[] varNames ={"jieguo", "huodong", "pay", "expire", "zhangdan", "every", "free", "i1", "a",
									"virtual", "ad", "spider", "mima", "pass", "ui"};
	public static String[] drink = {"ˮ","��","���","�̲�","����","�̲�","����","ţ��","����","��֭"
									,"��ζ��ˮ","�մ�ˮ","�˶�����","����","��"};
	
	private  String[] name ={"д��Ԫ����","ϴ��","����һ������","����","��������","�ع�","ʹ��%t",
		"����","����","����","�ύ��ְ����","�����н","���ϼӰ�","��������ǰ��ţ",
		"ߣ��","���������վ","��������\"%v\"","д����%l�еķ���","�ύ����","���븴��",
		"����","��dota","��������","�޸�BUG","�������","��������","��΢��","��ABվ"};
	
	private  String[] good = {"д��Ԫ���Խ����ٳ���","�㼸��ûϴ���ˣ�","","������������������˼ά����",
			"������������ǰ�ȫ��","���������õ����","�㿴��������Ʒλ","�÷���ʱ�ͷ���",
			"������ǧ����Ŀ����Դ������","���Թٽ�������ܺ�","��˾�ҵ���һ��������ܸɸ����˵ļһ�Ͳ�����Ͽ����",
			"�ϰ��������ܺ�","�����ǳ���Ա������õ�ʱ��","�����㰫��������","���⻺�������",
			"��ʰ�����������","","��Ĵ�����֯�ĺܺã���һ��û��ϵ","������ͻ�ļ�������͵�","������Ҫ����ļ��ʴ������",
			"д����֮�����һ�´������潡��","�㽫��������","�����ǳ���Ա������õ�ʱ��","������BUG�����������",
			"���������齫���ͷ�Է籩","","���췢����BUG����������ͷ�Է籩","","���췢�����²��ܴ��","����Ҫ������"};
	private  String[] bad = {"д��Ԫ���Իή����Ŀ���Ч��", "�����Ʒ�������ϴ��","����û���Ķ��٣��Ե�ȴ����","�������ˣ��������û��ϵ",
			"���ܵ��������Ժ��","����п��ܻ�������̶","���˻��������װ��","���ڵ�ǰ�ľ������ƣ������һ�ݹ���δ�ر�����ǿ","��ֻ���е�һ�����췹�Ե�����",
			"���Թٲ�ˬ�����������","���ڵ�ǰ�ľ������ƣ������һ�ݹ���δ�ر�����ǿ","��˾���ڿ��ǲ�Ա","�����ǳ���Ա������õ�ʱ��","�ᱻʶ��",
			"Сߣ���飬��ߣ����ǿߣ�ҷ�����","���������","","��Ĵ��뽫���Ҳ��������Լ���������","��������һ��ѳ�ͻ����������Լ��ǲ���ʱ�䴩Խ��",
			"��ʲô���ⶼ���ֲ��ˣ��װ��˷�ʱ��","��ᱻ��ʺ���ӱ��ڹ�","��ᱻŰ�ĺܲ�","������Ѿ���ƣ������","�²�����BUG�����޸��ĸ���",
			"���˽�ƣ�������������ô����","","�ᱻ�ϰ忴��","�ᱻ�ϰ忴��"};
	
	private static List<Map<String, Object>> specials;
	private static List<Map<String, String>> activities;
	private static List<String> drinks;
	
	public CalenderData() {
		setData();
	}
	
	private void setData(){
		specials = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", 20130221);
		map.put("type", "good");
		map.put("name", "������ϰ");
		map.put("description", "��һ�ĸ����Ӷ��ź˵���������");
		specials.add(map);
		
		drinks = new ArrayList<String>();
		for (String dri : drink) {
			drinks.add(dri);
		}
		
		activities = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < name.length; i++) {
			Map<String, String> actMap = new HashMap<String, String>();
			actMap.put("name", name[i]);
			actMap.put("good", good[i]);
			actMap.put("bad", bad[i]);
			activities.add(actMap);
		}
	}
	
	public List<Map<String, Object>> getSpecials(){
		return specials;
	}
	
	public List<String> getDrinks(){
		return drinks;
	}
	
	public List<Map<String, String>> getActivities(){
		return activities;
	}
}
