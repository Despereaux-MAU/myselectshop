package com.despereaux.myselectshop.aop;

import com.despereaux.myselectshop.entity.ApiUseTime;
import com.despereaux.myselectshop.entity.User;
import com.despereaux.myselectshop.repository.ApiUseTimeRepository;
import com.despereaux.myselectshop.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j(topic = "UseTimeAop")
@Aspect
@Component
@RequiredArgsConstructor
public class UseTimeAop {

    private final ApiUseTimeRepository apiUseTimeRepository;

    @Pointcut("execution(* com.despereaux.myselectshop.controller.ProductController.*(..))")
    private void product() {}
    @Pointcut("execution(* com.despereaux.myselectshop.controller.FolderController.*(..))")
    private void folder() {}
    @Pointcut("execution(* com.despereaux.myselectshop.naver.controller.NaverApiController.*(..))")
    private void naver() {}

    @Around("product() || folder() || naver()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 측정 시작 시간
        long startTime = System.currentTimeMillis();

        try {
            // 핵심 기능 수행
            Object output = joinPoint.proceed();
            return output;
        } finally {
            // 측정 종료 시간
            long endTime = System.currentTimeMillis();
            // 수행시간 = 종료시간 - 시작시간
            long runTime = endTime - startTime;

            // 로그인 회원이 없는 경우, 수행 시간 기록하지 않음
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class) {
                // 로그인 회원 정보
                UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                User loginUser = userDetails.getUser();

                // API 사용시간 및 DB 에 기록
                ApiUseTime apiUseTime = apiUseTimeRepository.findByUser(loginUser).orElse(null);
                if (apiUseTime == null) {
                    // 로그인 회원의 기록이 없으면
                    apiUseTime = new ApiUseTime(loginUser, runTime);
                } else {
                    // 로그인 회원의 기록이 이미 있으면
                    apiUseTime.addUseTime(runTime);
                }

                log.info("[API Use Time] Username: " + loginUser.getUsername() + ", Total Time: " + apiUseTime.getTotalTime() + " ms");
                apiUseTimeRepository.save(apiUseTime);
            }
        }
    }
}
