# What for?
 + for Application Server? Spring Boot Aapplication?
 + for Restful? Include View(Thymeleaf)?
 + simple layout project
 
# ENV
 + git
 + gradle 
 + java 1.8
 + kotlin 1.3.11
 + spring boot 2.1.1.RELEASE
 + hibernate? mybatis?
 + thymeleaf?

## Server
 + local Gretty?
 
## Mybatis 사용 시
 + Parameter Object: 
    - ParameterMap 객체를 만들어서 사용한다.
    - HashMap을 상속한다.
    - put(Objejct object)는 key값을 Class명으로 저장한다
 + Return Object
    - 각 mapper.xml에 정의 되어있는 resultMap(Mybatis제공)을 사용하여 리턴한다.

# Error Handler
 + 일반 Http Request:
    - 서버 Error => GlobalExceptionHandler.Exception, Session 및 로그인 Error => login redirect
 + Ajax 요청 시:
    - 페이지에서 비동기 요청 시, 구분을 하기 위해서 Header에 'X-is-Ajax=true'라는 헤더를 추가한다.(에러처리를 구분하여 일괄 처리하기 위함)
    - 서버 Error => GlobalExceptionhandler.AjaxException
    - Session 및 로그인 Error => 400 Error(정상 접근시 login redirect)
 + Exception Files (com.kt.airmap.onm.exception)
    - AjaxEception: ajax 요청 공통적으로 처리
    - IncorrectException: 잘못된 파라미터를 가지고 접근시 에러처리

# Utils (com.kolayout.aeee.utils)
 + file: 파일 관리. FileUtils 빈 사용 
 + message.MessageManager: 메세지를 다루는 객체, 뷰에 메세지를 MessageManager.MessageKey은 서버와 뷰 간의 약속키
 + Paging: 페이징 계산을 하는 객체
 + SessionKeyManager: 세션에 등록하는 키를 관리하는 객체