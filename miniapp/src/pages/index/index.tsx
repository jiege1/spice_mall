import React, { Component } from 'react'
import { View, Swiper, SwiperItem, Image } from '@tarojs/components'
import { observer, inject } from 'mobx-react'
import { Store } from 'src/store'
import css from './index.module.less';


interface Props {
  store?: Store
}

@inject('store')
@observer
export default class Index extends Component<Props> {
  componentWillMount () { }

  componentDidMount () { 
    // this.props.store?.product.update('categoryId', 123);
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    const { store } = this.props;
    return (
      <View className={css.container}>
        <Swiper className={css.slider} indicatorDots circular indicatorColor='#999' indicatorActiveColor='#333'>
          {
            store?.sliders.map((slider, index) => (
              <SwiperItem className={css.sliderItem} key={slider + index}>
                <Image mode='aspectFill' className={css.sliderImg} src={slider} />
              </SwiperItem>
            ))
          }
        </Swiper>
        <View className={css.itemContent}>
          <View>

          </View>
        </View>
      </View>
    )
  }
}
