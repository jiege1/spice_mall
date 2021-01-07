
export default class Utils {

  /**
   * 睡眠函数
   * @param time 
   */
  static async sleep(time = 1000): Promise<void> {
    return await new Promise(resolve => {
      setTimeout(resolve, time)
    });
  }

}
