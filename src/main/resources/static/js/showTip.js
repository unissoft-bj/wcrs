	var footer=document.getElementById("footer");
var conlist=document.getElementsByClassName('contact');
			var share=document.getElementById("share");
			var tip=document.getElementsByClassName('tip')[0];
			console.log(tip);
		//获得客服电话
			var contact=footer.getElementsByTagName('a')[1];
		//添加属性判断tip和遮罩的display
		tip.show=false;
		conlist[0].show=false;
		//给contact添加点击事件
		contact.onclick=function(){
			//设置遮罩层和弹窗的display:block
			for(var i=0;i<conlist.length;i++){
				conlist[i].style.display='block';
				}
			conlist[0].show=true;
			document.documentElement.style.overflow='hidden';
			
			};
		//给遮罩层添加点击事件
		conlist[0].onclick=function(){
			console.log(conlist[0].show);
			if(conlist[0].show){
				conlist[0].style.display='none';
				conlist[1].style.display='none';
				tip.style.display='none';
				conlist[0].show=false;
				tip.show=false;
				document.documentElement.style.overflow='auto';
				}
			};
		//给分享按钮添加点击事件
		share.onclick=function(){
			if(!tip.show){
			conlist[0].style.display='block';
			tip.style.display='block';
			tip.show=true;
			conlist[0].show=true;
				}
			document.documentElement.style.overflow='hidden';
			};
		//给提示添加点击事件
		tip.onclick=function(){
			if(tip.show){
			conlist[0].style.display='none';
			tip.style.display='none';
			tip.show=false;
			conlist[0].show=false;
			document.documentElement.style.overflow='auto';
				}
			};