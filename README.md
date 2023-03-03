기능명세서

| 요구사항 이름 | 요구사항 설명 | 중요도 | 비고 |
| --- | --- | --- | --- |
| 메인페이지 출력 | 사진 목록, 로그아웃 버튼, 게시글 작성 버튼, 좋아요 버튼 | ⭐⭐⭐ |  |
| 로그인 시 | 일정 시간 활동하지 않으면 토큰 삭제 | ⭐⭐⭐ |  |
| 비로그인시 |  | ⭐⭐⭐ |  |
| 회원가입 | 사용자의 아이디, 비밀번호, 이메일을 받아 서버에 저장 | ⭐⭐⭐ | username : 알파벳 소문자(a~z), 숫자(0~9) 중 5자 이상, 10자 이하password : 알파벳 대소문자(a~z,A~Z), 숫자(0~9) 중 5자 이상, 10자 이하 |
| 아이디 중복확인 | 회원가입시 아이디가 있는지 아이디를 서버에 보내 확인한다. | ⭐ | 회원가입시 확인 가능 |
| 로그아웃 | 로그아웃 클릭시 사용자의 쿠키를 삭제후 로그인 페이지로 이동 | ⭐⭐⭐ | 로그인된 사용자만 가능 |
| 게시글 작성 | 사진, 내용을 적어서 서버로 보낸다. | ⭐⭐⭐ | 로그인된 사용자만 가능 |
| 상세페이지 | 사용자, 사진, 내용을 서버에서 가져와서 화면에 보여준다. | ⭐⭐⭐ |  |
| 상세페이지 수정 / 삭제 | 상세페이지 내용을 수정하거나 삭제한다. | ⭐⭐⭐ | 게시글 작성자만 보이게 작성한다. |
| 댓글 | 해당 상세페이지의 댓글을 작성한다.| ⭐⭐⭐ | 로그인된 사용자만 가능 |
| 좋아요/좋아요 취소 | 게시글에 대한 좋아요 버튼 | ⭐ | 로그인된 사용자만 가능 |



와이어프레임

![스크린샷 2023-03-03 오후 1 42 12](https://user-images.githubusercontent.com/111184537/222634449-4d5da0f8-6404-4627-9b76-8b8d4fb6b23d.png)
![스크린샷 2023-03-03 오후 1 42 20](https://user-images.githubusercontent.com/111184537/222634458-80ddde2c-053e-4cb1-9011-caa22fb5a95f.png)
![스크린샷 2023-03-03 오후 1 42 29](https://user-images.githubusercontent.com/111184537/222634460-e9f1fd88-e60d-4b8f-bf0a-fe34e119ba5e.png)
![스크린샷 2023-03-03 오후 1 42 38](https://user-images.githubusercontent.com/111184537/222634468-80e367f1-c49e-48a9-93c5-26099121eb00.png)
![스크린샷 2023-03-03 오후 1 42 44](https://user-images.githubusercontent.com/111184537/222634470-2c5cbb0b-ec71-488e-9540-3d6fd062310f.png)
![스크린샷 2023-03-03 오후 1 42 50](https://user-images.githubusercontent.com/111184537/222634473-f742fb85-66b4-4632-88d4-c24456da1f13.png)
![스크린샷 2023-03-03 오후 1 42 57](https://user-images.githubusercontent.com/111184537/222634478-0d95a22f-14f8-448b-9d5f-5c33bb36cfa9.png)
![스크린샷 2023-03-03 오후 1 43 05](https://user-images.githubusercontent.com/111184537/222634483-d0c4f0ba-6961-4e22-bc15-8bc6e0a707e3.png)

프로젝트 발표

![스크린샷 2023-03-03 오후 1 39 46](https://user-images.githubusercontent.com/111184537/222634523-f73f49e0-a534-413e-ab3b-ca3efd7a31da.png)
![스크린샷 2023-03-03 오후 1 40 12](https://user-images.githubusercontent.com/111184537/222634526-9e223729-2646-47ff-824d-cff51b5682b2.png)
![스크린샷 2023-03-03 오후 1 40 37](https://user-images.githubusercontent.com/111184537/222634535-be746b36-8ca2-45d8-acd8-fed400ee57ef.png)
![스크린샷 2023-03-03 오후 ![스크린샷 2023-03-03 오후 1 40 57](https://user-images.githubusercontent.com/111184537/222634550-2c60ca7c-34bd-4504-8da8-6bfd55acf9d0.png)
1 40 46](https://user-images.githubusercontent.com/111184537/222634539-5c20ba63-5215-4731-b4e6-6c3a3b24417a.png)
![스크린샷 2023-03-03 오후 1 41 04](https://user-images.githubusercontent.com/111184537/222634574-61ab3a7b-5f24-472f-b733-a24d13e87ca9.png)
![스크린샷 2023-03-03 오후 1 41 19](https://user-images.githubusercontent.com/111184537/222634580-5fe7ab70-531a-4b93-853a-d6bf03fccb4a.png)
![스크린샷 2023-03-03 오후 1 41 28](https://user-images.githubusercontent.com/111184537/222634589-ea80e9a5-325e-4ee2-9e2b-92bd3d96cf19.png)
![스크린샷 2023-03-03 오후 1 41 44](https://user-images.githubusercontent.com/111184537/222634593-54098dcf-548b-45e1-bd83-1175c8f9e246.png)


API 명세서

https://www.notion.so/406df6cc9a4e4bc8827dffca857eb36f?v=d053b6a829134bc1a50dbd3f413c12b1
