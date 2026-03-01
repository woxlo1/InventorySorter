# 開発ガイド

## セットアップ手順

### 1. 必要なソフトウェア

- **Java JDK 17以上**: [Adoptium](https://adoptium.net/)からダウンロード
- **IntelliJ IDEA**: [無料Community版](https://www.jetbrains.com/idea/download/)
- **Git**: [公式サイト](https://git-scm.com/)からインストール

### 2. プロジェクトのインポート

```bash
# IntelliJ IDEAで開く
1. File → Open
2. build.gradleファイルを選択
3. "Open as Project"を選択
4. Gradleの自動インポートを待つ
```

### 3. 初回ビルド

```bash
# Gradle設定のダウンロードとビルド
./gradlew build

# 5-10分程度かかります（初回のみ）
```

### 4. Minecraftで実行

```bash
# クライアント起動
./gradlew runClient

# または、IntelliJ IDEAから
# Gradle → Tasks → forgegradle runs → runClient
```

## コード構成

```
src/main/java/com/yourname/inventorysorter/
├── InventorySorterMod.java      # メインMODクラス
├── KeyBindings.java             # キーバインド設定
├── InventorySorter.java         # ソート処理ロジック
└── ClientModEvents.java         # イベントハンドラー

src/main/resources/
├── META-INF/mods.toml           # MOD情報
└── assets/inventorysorter/
    └── lang/
        ├── en_us.json           # 英語翻訳
        └── ja_jp.json           # 日本語翻訳
```

## デバッグ方法

### ログ出力

```java
InventorySorterMod.LOGGER.info("デバッグメッセージ");
InventorySorterMod.LOGGER.error("エラーメッセージ", exception);
```

### IntelliJ IDEAでのデバッグ

1. ブレークポイントを設定
2. `runClient`タスクを右クリック → Debug
3. ゲーム内で該当機能を実行

## よくある問題と解決方法

### ビルドエラー

```bash
# Gradleキャッシュをクリア
./gradlew clean

# 依存関係を再取得
./gradlew --refresh-dependencies
```

### MODが認識されない

- `mods.toml`のmodIdが正しいか確認
- `build/libs/`に`.jar`ファイルが生成されているか確認

### ゲームがクラッシュする

- ログファイルを確認: `run/logs/latest.log`
- エラーメッセージからクラス名・行番号を特定

## カスタマイズ方法

### デフォルトキーを変更

`KeyBindings.java`の以下の部分を編集:

```java
GLFW.GLFW_KEY_R  // Rキー
↓
GLFW.GLFW_KEY_I  // Iキーに変更
```

### ソート順を変更

`InventorySorter.java`の`sortContainer`メソッド内:

```java
merged.sort(Comparator
    .comparing((ItemStack stack) -> stack.getItem().toString())
    .thenComparing(ItemStack::getCount, Comparator.reverseOrder())
);
```

## 次のステップ

- [ ] ホットバーの整理機能を追加
- [ ] 設定画面でソート順をカスタマイズ可能に
- [ ] サウンドエフェクトを追加
- [ ] 他のコンテナ（かまど、エンダーチェストなど）に対応
