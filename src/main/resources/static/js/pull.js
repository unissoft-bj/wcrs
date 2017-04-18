		var content=document.getElementsByClassName("content")[0];
		var img=content.getElementsByTagName('img')[0];
		var pull=content.getElementsByTagName('h3')[0];
		var list=content.getElementsByTagName('ul')[0];
		var flag=false;
		//下拉显示列表
		pull.onclick=function(){
			/*
			用一个全局变量flag表示下拉显示列表的状态,false时点击收起，true时点击下拉显示
			*/
			if(!flag)
			{img.src='../images/right.png';
			list.style.display='none';
			flag=true;
			}else{
				img.src='../images/down.png';
				list.style.display='block';
				flag=false;
				}
			};
