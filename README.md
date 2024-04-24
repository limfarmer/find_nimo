
  <div>
    <img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=니모를%20찾아조!&fontSize=90" />
    <h2>http://localhost:8111/members/login</h2>

  </div>
 <div  align=center>
   <p>📚 Tech Stack 📚 </p>
      <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
      <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
      <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white">
<!--       <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"> -->
      <img src="https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white">
      <img src="https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white">
  </div>
  <div>
    <h2>프로젝트 소개</h2>
    <p>니모를 찾아조!는 풀스택 개발 취준생 4명이 만든 프로젝트로 반려동물을 분양 및 입양 글을 자유롭게 적어 새로운 가족을 만날 수 있게 돕는 프로젝트 입니다. 해당 프로젝트는 스프링부트와 리액트를 공부하기 앞서 스프링 MVC 패턴의 이해와 DB 공부를 중심으로 만들어진 프로젝트로 이후의 발전을 기대해주세요!😊</p>
  </div>
  <div>
    <h2>Spring MVC</h2>
    <p>
      MVC 패턴은 어플리케이션을 세 가지 영역, 즉 모델(Model), 뷰(View), 컨트롤러(Controller)로 구분하여 작업을 분리하는 웹 어플리케이션 개발 방법론입니다. 저희는 이번 Model은 JDBC를 선택했고 그 중 Oracle DB를 사용했습니다.
    </p>
    <p>
      <img src="https://github.com/limfarmer/find_nimo/assets/129915370/5c44308c-c302-448c-9467-c42cee2bb962">
<!--       <img src="https://github.com/limfarmer/find_nimo/assets/129915370/2567fa7f-0563-49f3-bf10-a5dbc24b90de"> -->
    </p>
  </div>
  <div>
    <h2>프로젝트 기능 소개</h2>
    <p>
      <h3>1. 로그인 페이지</h3>
      <h5>1. 로그인</h5>
      <p>
      첫 화면 페이지입니다. 아이디와 비밀번호를 입력받고 아이디와 비밀번호가 일치할 경우 세션에 해당 아이디를 저장하는 방식을 통해 구현 했습니다.
      </p>
      <img src="https://github.com/limfarmer/find_nimo/assets/129915370/1be9db82-e99d-407b-ab40-e4fb9f4e8ad3">
    <h5>2. 회원가입</h5>
    <p>
      아이디와 비밀번호가 없을 시 로그인 페이지 하단의 회원가입을 누르면 회원가입 페이지로 이동하여 회원가입을 진행할 수 있습니다.
    </p>
    <img src="https://github.com/limfarmer/find_nimo/assets/129915370/25b5f74f-52b6-4f38-b163-1d11b9acf7f9">
    </p>
    <p>
      <h3>2. 메인 페이지</h3>
      <p>
        로그인 후 이동하는 페이지로 Header와 Footer를 적용해 다른 페이지로 이동 할 수 있게 구현 했습니다. Footer에는 로그아웃 기능을 넣어 로그아웃시 세션에 저장된 아이디 값을 지우고 다시 로그인 페이지로 이동하게 됩니다.
      </p>
      <img src="https://github.com/limfarmer/find_nimo/assets/129915370/29eb2afb-d86d-40b1-965d-c0791708e6e2">
    </p>
    <p>
      <h3>3. 분양 페이지</h3>
      <h5>1. 분양 전체 페이지</h5>
      <p>
        아직 분양이 안된 반려동물의 전체 리스트를 보여주는 페이지로 제목을 클릭 시 해당 글의 상세 페이지로 이동하게 됩니다.
      </p>
      <img src="https://github.com/limfarmer/find_nimo/assets/129915370/522ace50-74c7-4f83-8e10-eac99fa997b0">
    </p>
    <h5>2. 분양 상세 페이지</h5>
    <p>
      상세페이지 하단의 분양받기 버튼을 클릭시 분양이 완료되어 전체페이지에 더이상 보이지 않으며 마이페이지의 내가 분양한 리스트에 추가가 됩니다.
    </p>
    <img src="https://github.com/limfarmer/find_nimo/assets/129915370/07fde7b1-9eeb-4d58-b0bf-4f9b2da958cd">
    <p>
    <h3>4. 후기 페이지</h3>
      <p>
        분양이 된 동물들 중 입양자가 쓴 후기 전체 리스트를 보여주는 페이지입니다. 제목을 클릭 시 후기 상세페이지로 이동하고 하단의 뒤로가기 클릭 시 다시 후기페이지로 이동하게 됩니다.
      </p>
      <img src="https://github.com/limfarmer/find_nimo/assets/129915370/77a0751e-58da-4f09-a1cf-4ecb4b75dd2f">
    </p>
  </div>
  
<!-- <img src="https://github.com/limfarmer/find_nimo/assets/129915370/13e91c1d-98e6-4f6f-9ec8-a9c92fa2cd09"> -->


