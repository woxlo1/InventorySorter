# Inventory Sorter Mod

Minecraftのインベントリとチェストを自動整理するシンプルなMODです。

## 機能

- **キーボードショートカット**: デフォルトで`R`キーを押すとインベントリを整理
- **自動スタック**: 同じアイテムを自動的にまとめます
- **自動ソート**: アイテムをID順に並べ替え
- **チェスト対応**: プレイヤーのインベントリだけでなく、チェストも整理可能

## 動作環境

- Minecraft: 1.20.1
- Forge: 47.2.0以上
- Java: 17以上

## インストール方法

1. [Minecraft Forge](https://files.minecraftforge.net/)をインストール
2. ビルドした`.jar`ファイルを`mods`フォルダに配置
3. Minecraftを起動

## 開発者向け

### ビルド方法

```bash
# プロジェクトのセットアップ
./gradlew build

# 開発環境で実行
./gradlew runClient
```

### Git運用

- `develop`ブランチ: 開発用
- `main`ブランチ: 安定版
- Pull Requestを使用してdevelopからmainへマージ

## ライセンス

MIT License

## 作者

YourName
