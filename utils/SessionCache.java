package framework.utils;

import java.util.HashMap;
import java.util.Map;

public class SessionCache {
    static {
        sessionData_ = new HashMap<String, Object>();
    }

    public static Object getSessionData(String sessionId) {
        synchronized(sessionData_) {
            if (sessionData_.containsKey(sessionId)) {
                return sessionData_.get(sessionId);
            }

            return null;
        }
    }

    public static void setSessionData(String sessionId, Object dataObject) {
        synchronized(sessionData_) {
            sessionData_.put(sessionId, dataObject);
        }
    }

    public static void clearSessionData(String sessionId) {
        synchronized(sessionData_) {
            if (sessionData_.containsKey(sessionId)) {
                sessionData_.remove(sessionId);
            }
        }
    }

    private static Map<String, Object> sessionData_;
}
