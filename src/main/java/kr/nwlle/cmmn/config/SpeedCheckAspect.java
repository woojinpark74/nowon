package kr.nwlle.cmmn.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class SpeedCheckAspect {

    ThreadLocal<StringBuffer> threadLocal = new ThreadLocal<StringBuffer>();

    @Pointcut("within(@org.springframework.stereotype.Controller *) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerPointCut() {
    }

    @Around("controllerPointCut()")
    public Object controllerExecuteTime(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch watch = new StopWatch("controllerExecuteTime");
        watch.start();

        clearModelMapIfJsonRequest(pjp);

        Object retval = pjp.proceed();

        watch.stop();

        logging("Conterller", pjp, watch);

        // setTimeStamp(watch);

        return retval;
    }

    /**
     * .json 요청일 경우 Model의 map 객체를 clear 시킨다.
     */
    private void clearModelMapIfJsonRequest(ProceedingJoinPoint pjp) {

        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.currentRequestAttributes()).getRequest();
        if (!ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString().endsWith(".json"))
            return;

        for (Object object : pjp.getArgs()) {
            if (object instanceof Model) {
                Model model = (Model) object;
                model.asMap().clear();
                break;
            }
        }
    }

    @Around("execution(* org.springframework.web.servlet.view.AbstractView.render(..))")
    public Object jstlViewRenderTime(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch watch = new StopWatch("jstlViewRenderTime");
        watch.start();

        Object retval = pjp.proceed();

        watch.stop();

        logging("View(html rendering)", pjp, watch);

        // setTimeStamp(watch);

        return retval;
    }

    private void logging(String layer, ProceedingJoinPoint pjp, StopWatch watch) {

        String qs = null;
        for (Object arg : pjp.getArgs()) {
            if (arg instanceof HttpServletRequest) {
                qs = ((HttpServletRequest) arg).getQueryString();
                break;
            }
        }

        if (log.isDebugEnabled()) {
            if (StringUtils.isNotBlank(qs))
                log.debug("[{}] /{}?{} : {}(ms) elapsed", layer, pjp.getSignature().getName(), qs, watch.getTotalTimeMillis());
            else
                log.debug("[{}] /{} : {}(ms) elapsed", layer, pjp.getSignature().getName(), watch.getTotalTimeMillis());
        }
    }

    private void setTimeStamp(StopWatch watch) {
        threadLocal.set(threadLocal.get().append(StringUtils.rightPad(watch.getId(), 25, " ")).append("elapsed ").append(watch.getTotalTimeMillis()).append("(ms)\n"));
    }
}
