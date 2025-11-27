package com.swingui.front;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;

import com.swingui.widget.Framer;

/**
 * フレーム提供クラス
 * 
 * @author t.yoshida
 */
public class Frame
{
    /**
     * 指定されたタイトルと子コンポーネントでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param child フレームに表示する子コンポーネント
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, JComponent child)
    {
        return of(title, null, child);
    }

    /**
     * 指定されたタイトルと追加オプションでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param extraOption フレームに適用する追加オプション
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, Framer.Option extraOption)
    {
        return of(title, extraOption, null);
    }

    /**
     * 指定されたタイトル、追加オプション、子コンポーネントでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param extraOption フレームに適用する追加オプション
     * @param child フレームに表示する子コンポーネント
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, Framer.Option extraOption, JComponent child)
    {
        Framer frame = new Framer(title);
        if(child != null)
        {
            /*↓ウィンドウ拡大しても子コンポーネントを中心に配置させるため書き換え
            frame.getContentPane().add(child, BorderLayout.CENTER);*/

            GridBagLayout layout = new GridBagLayout();
            frame.getContentPane().setLayout(layout);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE; // child 自体はリサイズ不可
            frame.getContentPane().add(child, gbc);
        }

        //
        // フレームへの初期化オプション設定
        //
        new Framer.BasicOption().setUp(frame);  // 基本オプションの実施
        if(extraOption != null)
        {
            // フレームへの追加オプション設定
            extraOption.setUp(frame);
        }

        // フレームの表示
        frame.setVisible(true);

        return frame;
    }
}
