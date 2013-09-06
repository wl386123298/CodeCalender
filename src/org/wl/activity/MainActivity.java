package org.wl.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.wl.data.CalenderData;
import org.wl.util.DataUtil;
import org.wl.util.ScreenShot;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.chinacodecalender.R;
public class MainActivity extends Activity {
	private TextView calendar_date,calendar_orientation_content,calendar_drink_content,calendar_peach_content;
	private ListView calendar_good_content,calendar_bad_content;
	private Calendar calendar = Calendar.getInstance();
	private List<Map<String, String>> goodList,badList;	
	private TextView actionBarText;
	private int titleId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findView();	
		getActionBar().setDisplayShowHomeEnabled(false);
		actionBarText.setTextColor(getResources().getColor(R.color.white));
		
		calendar_date.setText(String.format("今天是%s年%s月%s日   星期%s",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH),CalenderData.weeks[calendar.get(Calendar.DAY_OF_WEEK)-1]));
		
		DataUtil util = new DataUtil();
		goodList = util.getGooList();
		badList = util.getBadList(); 
		
		//System.out.println(badList);
		
		//宜事adapter
		SimpleAdapter goodAdapter = new SimpleAdapter(this, goodList, R.layout.list_adapter,
				new String[]{"name","good"},new int[]{R.id.title,R.id.content});
		calendar_good_content.setAdapter(goodAdapter);
		calendar_good_content.setCacheColorHint(Color.TRANSPARENT);
		
		//不宜事adapter
		SimpleAdapter badAdapter = new SimpleAdapter(this, badList, R.layout.list_adapter,
				new String[]{"name","bad"},new int[]{R.id.title,R.id.content});
		calendar_bad_content.setAdapter(badAdapter);
		calendar_bad_content.setCacheColorHint(Color.TRANSPARENT);
		
		calendar_orientation_content.setText(String.format("面向%s写程序，BUG最少", util.getDirection()));
		calendar_drink_content.setText(util.getDrinks());
		calendar_peach_content.setText(String.valueOf(util.getGirlValue()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_icon:
			new ScreenShot().shoot(this);
			Intent intent=new Intent(Intent.ACTION_SEND); 
			intent.setType("image/*"); 
			intent.setType("text/*");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Share"); 
			intent.putExtra(Intent.EXTRA_TEXT, "#程序员老黄历#"); 
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("file://sdcard/ChinaCodeCalender/");
			stringBuilder.append(getFullFormatDate());
			stringBuilder.append(".png");
			
			intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(stringBuilder.toString()));
			startActivity(Intent.createChooser(intent, getTitle()));
	
			break;

		}
		return super.onOptionsItemSelected(item);
	}
	
	private String getFullFormatDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}
	
	protected void findView(){
		titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");		
		calendar_date = (TextView)findViewById(R.id.calendar_date);
		calendar_good_content = (ListView)findViewById(R.id.calendar_good_content);
		calendar_bad_content = (ListView)findViewById(R.id.calendar_bad_content);
		calendar_orientation_content = (TextView)findViewById(R.id.calendar_orientation_content);
		calendar_drink_content = (TextView)findViewById(R.id.calendar_drink_content);
		calendar_peach_content = (TextView)findViewById(R.id.calendar_peach_content);
		actionBarText = (TextView) findViewById(titleId);
	}
}
