function setRem(){
	 var dpr,rem,scale,width;
	//获得根节点
	var docEl=document.documentElement;
	//创建一个style标签
	var fontEl=document.createElement("style");
	//根据选择器，返回一个DOM元素
	var metaEl=document.querySelector("meta[name='viewport']");
	console.log(metaEl);
	//获得设备的设备像素比
	dpr=window.devicePixelRatio||1;
	rem=docEl.clientWidth*dpr/10;
	scale=1/dpr;
	width=dpr*docEl.clientWidth;
	//设置viewport,进行缩放，达到高清效果
	metaEl.setAttribute('content', 'width=' + width + ',user-scalable=no');

	//设置data-dpr属性，留作CSS hack之用
	docEl.setAttribute("data-dpr",dpr);
	//动态写入样式
	docEl.firstElementChild.appendChild(fontEl);
	//根据dpr的值，分别判断width，并设置相应的rem
	if(dpr>=3){
		if(width>1350) rem=135;
		}else if(dpr>=2){
			if(width>900) rem=90;
			}else{
				if(width>450) rem=45;
				}
	fontEl.innerHTML="html{font-size:"+rem+"px!important;}";
	}