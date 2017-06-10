package cn.edu.fjnu.utils ;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.common.RuleException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 *
 * @author zhy
 *
 */
public class ExtractService
{
	/**
	 * @param rule
	 * @return
	 */

	public static List<NewsEntity> extract(String url)
	{

		// 进行对url的必要校验
		validateRule(url);

		List<NewsEntity> datas = new ArrayList<NewsEntity>();
		NewsEntity data = null;
		try
		{
			/**
			 * 解析rule
			 */
			//String url = rule.getUrl();

			Connection conn = Jsoup.connect(url);

			// 设置请求类型
			Document doc = conn.timeout(100000).get();

			//处理返回数据
			Elements results = new Elements();
			results = doc.getElementsByTag("body");

			int count =0;	//用于限制爬取信息数量
			for (Element result : results)
			{
				if(count++>10){
					break;
				}
				Elements links = result.select("li");
				for (Element link : links)
				{
					//必要的筛选
					Elements aa = link.getElementsByTag("a");
					Pattern pattern =  Pattern.compile(".*减肥.*");
					Pattern pattern2 =  Pattern.compile(".*html");
					String linkHref = aa.attr("href");
					String linkText = link.text();
					Matcher m = pattern.matcher(linkText);
					Matcher m2 = pattern2.matcher(linkHref);
					if(m2.matches()){
						if(m.matches()){
							if(count++>10){
								break;
							}
							data = new NewsEntity();
							data.setLinkHref(linkHref);
							data.setLinkText(linkText);
							data.setLinkPic(getPic(linkHref));
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
							data.setTime(df.format(new Date()));  //存入系统时间
							datas.add(data);
						}
					}
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return datas;
	}
	/**
	 *
	 * @param link
	 * @return
	 * @throws IOException
	 * 获得图片src
	 */
	private static String getPic(String link) throws IOException{
		// TODO Auto-generated method stub
		String Pic=null;
		Document doc = Jsoup.connect(link).get();
		Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
		for(Element e : pngs){
			String src=e.attr("src");//获取img中的src路径
			Pattern pattern =  Pattern.compile(".*button.*");
			Pattern pattern2 =  Pattern.compile(".*weixin.*");
			Matcher m = pattern.matcher(src);
			Matcher m2 = pattern2.matcher(src);
			if(!m.matches()){
				if(!m2.matches()){
					Pic = src;
					break;
				}
			}
		}
		return Pic;
	}


	/**
	 * 对传入的参数进行必要的校验
	 */

	private static void validateRule(String url)
	{
		if (isEmpty(url))
		{
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://"))
		{
			throw new RuleException("url的格式不正确");
		}

	}

}
