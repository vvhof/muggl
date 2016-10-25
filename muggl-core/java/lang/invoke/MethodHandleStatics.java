/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package java.lang.invoke;

import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Unsafe;

/**
 * This class consists exclusively of static names internal to the
 * method handle implementation.
 * Usage:  {@code import static java.lang.invoke.MethodHandleStatics.*}
 * @author John Rose, JSR 292 EG
 */
/*non-public*/ class MethodHandleStatics {

    private MethodHandleStatics() { }  // do not instantiate

    static final Unsafe UNSAFE = Unsafe.getUnsafe();

    static final boolean DEBUG_METHOD_HANDLE_NAMES;
    static final boolean DUMP_CLASS_FILES;
    static final boolean TRACE_INTERPRETER;
    static final boolean TRACE_METHOD_LINKAGE;
    static final int COMPILE_THRESHOLD;
    static final int DONT_INLINE_THRESHOLD;
    static final int PROFILE_LEVEL;

    static {
        final Object[] values = new Object[7];
        
        // Fixme mxs edited this class in the following lines:
        DEBUG_METHOD_HANDLE_NAMES = (Boolean) false;
        DUMP_CLASS_FILES          = (Boolean) false;
        TRACE_INTERPRETER         = (Boolean) false;
        TRACE_METHOD_LINKAGE      = (Boolean) false;
        COMPILE_THRESHOLD         = (Integer) 0;
        DONT_INLINE_THRESHOLD     = (Integer) 30;
        PROFILE_LEVEL             = (Integer) 0;
    }

    /** Tell if any of the debugging switches are turned on.
     *  If this is the case, it is reasonable to perform extra checks or save extra information.
     */
    /*non-public*/ static boolean debugEnabled() {
        return (DEBUG_METHOD_HANDLE_NAMES |
                DUMP_CLASS_FILES |
                TRACE_INTERPRETER |
                TRACE_METHOD_LINKAGE);
    }

    /*non-public*/ static String getNameString(MethodHandle target, MethodType type) {
        if (type == null)
            type = target.type();
        MemberName name = null;
        if (target != null)
            name = target.internalMemberName();
        if (name == null)
            return "invoke" + type;
        return name.getName() + type;
    }

    /*non-public*/ static String getNameString(MethodHandle target, MethodHandle typeHolder) {
        return getNameString(target, typeHolder == null ? (MethodType) null : typeHolder.type());
    }

    /*non-public*/ static String getNameString(MethodHandle target) {
        return getNameString(target, (MethodType) null);
    }

    /*non-public*/ static String addTypeString(Object obj, MethodHandle target) {
        String str = String.valueOf(obj);
        if (target == null)  return str;
        int paren = str.indexOf('(');
        if (paren >= 0) str = str.substring(0, paren);
        return str + target.type();
    }

    // handy shared exception makers (they simplify the common case code)
    /*non-public*/ static InternalError newInternalError(String message) {
        return new InternalError(message);
    }
    /*non-public*/ static InternalError newInternalError(String message, Throwable cause) {
        return new InternalError(message, cause);
    }
    /*non-public*/ static InternalError newInternalError(Throwable cause) {
        return new InternalError(cause);
    }
    /*non-public*/ static RuntimeException newIllegalStateException(String message) {
        return new IllegalStateException(message);
    }
    /*non-public*/ static RuntimeException newIllegalStateException(String message, Object obj) {
        return new IllegalStateException(message(message, obj));
    }
    /*non-public*/ static RuntimeException newIllegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }
    /*non-public*/ static RuntimeException newIllegalArgumentException(String message, Object obj) {
        return new IllegalArgumentException(message(message, obj));
    }
    /*non-public*/ static RuntimeException newIllegalArgumentException(String message, Object obj, Object obj2) {
        return new IllegalArgumentException(message(message, obj, obj2));
    }
    /** Propagate unchecked exceptions and errors, but wrap anything checked and throw that instead. */
    /*non-public*/ static Error uncaughtException(Throwable ex) {
        if (ex instanceof Error)  throw (Error) ex;
        if (ex instanceof RuntimeException)  throw (RuntimeException) ex;
        throw newInternalError("uncaught exception", ex);
    }
    static Error NYI() {
        throw new AssertionError("NYI");
    }
    private static String message(String message, Object obj) {
        if (obj != null)  message = message + ": " + obj;
        return message;
    }
    private static String message(String message, Object obj, Object obj2) {
        if (obj != null || obj2 != null)  message = message + ": " + obj + ", " + obj2;
        return message;
    }
}