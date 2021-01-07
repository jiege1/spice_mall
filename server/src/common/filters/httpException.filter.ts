import { ExceptionFilter, Catch, ArgumentsHost, HttpException, HttpStatus } from '@nestjs/common';
import { Response } from 'express';
import { BusinessException } from '../utils/result/result';
import { ErrorCodeMsg } from '../utils/result/result.code';

/**
 * 错误码为 402 时，为业务错误，
 * 将转化为 httpStatue = 200, body = ErrorCode 形式输出前端
 */
@Catch(BusinessException)
export class BusinessHttpExceptionFilter implements ExceptionFilter {
  catch(exception: HttpException, host: ArgumentsHost): void {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const status = exception.getStatus();

    if (status === HttpStatus.PAYMENT_REQUIRED) {
      const businessException = exception as BusinessException;
      const code = businessException.code;

      response
        .status(HttpStatus.OK)
        .json({
          code: code,
          msg: ErrorCodeMsg[code],
          data: null,
        });
    }
  }
}