package com.redarc.lteweb;

import org.apache.ecs.html.Script;

import com.redarc.BaseWeb;

/*
<html>
<body>
<h1 style="text-align:center;font-family:arial;color:red";>WELCOME</h1>
<script type="text/javascript">
    var httpServer = "http://10.186.135.173/";
	var address=new Array();
	address[0]=httpServer.concat("page1.html");
	address[1]=httpServer.concat("page2.html");
	address[2]=httpServer.concat("page3.html");
	address[3]=httpServer.concat("page4.html");
	address[4]=httpServer.concat("page5.html");
	address[5]=httpServer.concat("page6.html");
    address[6]=httpServer.concat("Daily Report.html");
    address[7]=httpServer.concat("PGslide.html");
	var mywindow=window.open(address[0]);
	var i=1;
	setInterval("page(i)",30000);
	function page(j)
	{
		if(j==address.length)
		{
			i=0;
		}
		mywindow.location.href=address[i];
		i++;
	}
</script>

</body>
</html>

 */
public class WebIndex extends BaseWeb{

	@Override
	public String body() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String style() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String script() {
		// TODO Auto-generated method stub
		StringBuffer content = new StringBuffer();
		content.append("var httpServer = \"http://10.186.135.173/\";");
		content.append("var address=new Array();");
		
		content.append("address[0]=httpServer.concat('page1.html');");
		content.append("address[1]=httpServer.concat('page1.html');");
		content.append("address[2]=httpServer.concat('page1.html');");
		content.append("address[3]=httpServer.concat('page1.html');");
		content.append("address[4]=httpServer.concat('page1.html');");
		
		content.append("var mywindow=window.open(address[0]);");
		content.append("var i=1;");
		content.append("setInterval('page(i)',30000);");
		content.append("function page(j){");
		content.append("if(j==address.length){");
		content.append("i=0;");
		content.append("}");
		content.append("mywindow.location.href=address[i];");
		content.append("}");

		Script script = new Script();
		script.setTagText(content.toString());
		return script.toString();
	}

}
