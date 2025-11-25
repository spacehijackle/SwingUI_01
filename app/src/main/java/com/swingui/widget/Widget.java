package com.swingui.widget;

import java.awt.Color;
import java.util.function.Consumer;

import javax.swing.JComponent;

import com.swingui.value.UIValue;

/**
 * Swingの各GUI部品（ウィジット）を宣言的UI用に拡張するインターフェース定義
 * 
 * @author t.yoshida
 *
 * @param <T> GUI部品クラス
 */
public interface Widget<T extends JComponent>
{
    /**
     * パディングの設定をする。
     * 
     * @param padding パディング値
     * @return 自身のインスタンス
     */
    T padding(int padding);

    /**
     * 活性/非活性の設定をする。
     * 
     * @param isEnabled true: 活性, false: 非活性
     * @return 自身のインスタンス
     */
    T enabled(UIValue<Boolean> isEnabled);

    /**
     * 自身のサイズの設定をする。
     * 
     * @param width 幅
     * @param height 高さ
     * @return 自身のインスタンス
     */
    T frame(int width, int height);

    /**
     * フォーカスの設定をする。
     * 
     * @param hasFocus フォーカス・フラグ（true: フォーカスを得る）
     * @return 自身のインスタンス
     */
    T focus(UIValue<Boolean> hasFocus);

    /**
     * 背景色の設定をする。
     * 
     * @param bgColor 背景色
     * @return 自身のインスタンス
     */
    T background(UIValue<Color> bgColor);

    /**
     * 引数経由で自身のインスタンスを渡す。
     * 
     * @apiNote SwingUI で未実装機能へのアクセス用
     * 
     * @param self 自身へのインスタンス参照
     * @return 自身のインスタンス
     */
    T self(Consumer<T> self);

    /**
     * 自身のコンポーネント表示を更新する。
     */
    void refreshWT();

    /**
     * 保持しているリソースを破棄する。
     */
    void dispose();
}
