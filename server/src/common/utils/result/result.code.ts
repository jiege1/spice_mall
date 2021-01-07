
export const enum SuccessCode {
  Success = 0,
}

/**
 * 错误码
 */
export const enum ErrorCode {
  UnknownError = 1,

  /**
   * 用户模块错误，code统一定义在 1000 - 1999 错误码端
   */
  LoginFail = 1001,
  UsernameRepeatError = 1101,
}

/**
 * 错误码对应的中文描述
 * httpExceptionFilter 全局拦截器，拦截到 httpStatus = 402 时
 * 根据 HttpException 对象的 code，获取到 ErrorCodeMsg 值后，并输入到 data 错误提示中
 */
export const ErrorCodeMsg: { [key in ErrorCode]: string } = {
  [ErrorCode.UnknownError]: '未知业务错误',
  [ErrorCode.LoginFail]: '用户名 或 密码错误',
  [ErrorCode.UsernameRepeatError]: '用户名重复',
}
