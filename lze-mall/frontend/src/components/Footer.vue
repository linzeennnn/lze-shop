<template>
  <footer className="nb-footer ">
    <div className="container">
      <div className="row">
        <div className="col-sm-12">
          <div className="about">
            <div className="social-media">
              <ul className="social-list">
               <li><a href="https://www.douyin.com/" target="_blank">
  <img src="https://www.douyin.com/favicon.ico" />
</a></li>


<li><a href="https://weibo.com/" target="_blank">
  <img src="https://weibo.com/favicon.ico" />
</a></li>

<li><a href="https://www.xiaohongshu.com/" target="_blank">
  <img src="https://www.xiaohongshu.com/favicon.ico" />
</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>

    <section className="copyright">
      <div className="container">
        <div className="row">
          <div className="col-sm-12 text-center">
            <p>202303038515林泽恩</p>
          </div>
        </div>
      </div>
    </section>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style>
.social-list {
  display: flex;
  list-style: none;
  justify-content: center;
  padding: 0;
}

.social-list li {
  opacity: 0.7;
  padding: 7px;
  color: #fca552;
  font-size: 19px;
}
.social-list li:hover{
  background-color: transparent !important;
  opacity: 1;
}
footer.nb-footer {
  margin-top: 200px;
  background: #f38627d2;
  border-top: 4px solid #e87022;
}

footer.nb-footer .about {
  margin: 0 auto;
  margin-top: 0px;
  max-width: 1170px;
  text-align: center;
}

footer.nb-footer .about p {
  font-size: 13px;
  color: #999;
  margin-top: 0px;
}

footer.nb-footer .about .social-media {
  margin-top: 0px;
}

footer.nb-footer .about .social-media ul li a {
  display: inline-block;
  width: 35px;
  height: 35px;
  border-radius: 100%;
  font-size: 15px;
  color: #fca552;
}

footer.nb-footer .about .social-media ul li a:hover {
  background: #32c5f8;
  color: #fff;
  border-color: #2cd3dc;
}

footer.nb-footer .footer-info-single {
  margin-top: 0px;
}

footer.nb-footer .footer-info-single .title {
  color: #aaa;
  text-transform: uppercase;
  font-size: 16px;
  border-left: 4px solid #fca552;
  padding-left: 5px;
}

footer.nb-footer .footer-info-single ul li a {
  display: block;
  color: #aaa;
  padding: 2px 0;
}

footer.nb-footer .footer-info-single ul li a:hover {
  color: #fca552;
}

footer.nb-footer .footer-info-single p {
  font-size: 13px;
  line-height: 20px;
  color: #aaa;
}

footer.nb-footer .copyright {
  margin-top: 0px;
  background: #e87022;
  padding: 0px 0;
  color: rgb(255, 255, 255);
}

footer.nb-footer .copyright p {
  margin: 0;
  padding: 0;
}

footer {
  
}
</style>
