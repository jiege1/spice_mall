import { HttpException, HttpStatus } from "@nestjs/common";
import { ErrorCode, SuccessCode } from "./result.code";

export { ErrorCode };

// 基础返回结果
interface CommonResult<D, C extends  ErrorCode | SuccessCode> {
  code: C;
  msg: string;
  data: D;
}

// 成功返回
export type OkResult<D> = CommonResult<D, SuccessCode>;

// 基础列表
export type ListResult<I> = OkResult<{ list: I[] }>

// 分页列表
export type PagListResult<I> = OkResult<{ list: I[], total: number }>

// 错误返回
export type ErrorResult = CommonResult<null, ErrorCode>;

// 业务异常处理，使用 402 错误码，全局 拦截器 拦截到 402，
export class BusinessException extends HttpException {

  code: ErrorCode;

  constructor(code: ErrorCode) {
    super('BusinessException', HttpStatus.PAYMENT_REQUIRED);
    this.code = code;
  }
}

// 结果返回 通用utils
export class Result {

  /**
   * 
   * @param data 
   */
  static ok<D>(data: D): OkResult<D> {
    return { code: SuccessCode.Success, msg: '', data };
  }

  /**
   * 通用列表返回
   * @param list 
   */
  static list<I>(list: I[]): ListResult<I> {
    return { 
      code: SuccessCode.Success, 
      msg: '', 
      data: {
        list
      }
    };
  }

  /**
   * 通用 分页列表 返回
   * @param list 
   */
  static pagList<I>(list: I[], total: number): PagListResult<I> {
    return { 
      code: SuccessCode.Success, 
      msg: '', 
      data: {
        total,
        list
      }
    };
  }

  /**
   * 统一业务错误返回
   * @param msg 前端提示错误信息
   * @param code 错误码
   */
  static error(
    { msg, code = ErrorCode.UnknownError }: { msg: string, code?: ErrorCode }
  ): ErrorResult {
    return { code, msg, data: null };
  }

}
