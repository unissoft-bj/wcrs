axios.get('http://59.110.113.110/secure/user/oEZJk0VlOT4P1x4647bzWS8YdbDg', {})
        .then(function (res) {
          document.getElementById('headimgurl').src = res.data.headimgurl;
          document.getElementById('nickName').innerHTML = res.data.nickName;
          document.getElementById('province').innerHTML = res.data.province;
          document.getElementById('city').innerHTML = res.data.city;
          document.getElementById('QrcodeUrl').src= 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket='+res.data.ticket;
          document.getElementById('qrcode').style.background= "url('https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket='+res.data.ticket)";
          });