# Git運用ガイド

## 初期セットアップ

```bash
# Gitリポジトリを初期化
git init

# developブランチを作成して切り替え
git checkout -b develop

# 初回コミット
git add .
git commit -m "Initial commit: Basic inventory sorter mod"

# mainブランチを作成（まだ切り替えない）
git branch main

# GitHubにリポジトリを作成後、リモートを追加
git remote add origin https://github.com/あなたのユーザー名/inventory-sorter-mod.git

# developブランチをpush
git push -u origin develop
git push -u origin main
```

## 開発フロー

### 1. 新機能の開発

```bash
# developブランチで作業
git checkout develop

# 変更をコミット
git add .
git commit -m "feat: Add new sorting algorithm"

# リモートにpush
git push origin develop
```

### 2. テストと動作確認

```bash
# Minecraftで動作確認
./gradlew runClient

# 問題なければ次のステップへ
```

### 3. mainブランチへのマージ（Pull Request）

```bash
# GitHubでPull Requestを作成
# develop → main

# または、ローカルでマージする場合:
git checkout main
git merge develop
git push origin main

# タグを付けてリリース
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

## コミットメッセージの規約

- `feat:` 新機能追加
- `fix:` バグ修正
- `docs:` ドキュメント変更
- `refactor:` コードのリファクタリング
- `test:` テストの追加・修正
- `chore:` ビルド設定などの変更

例:
```
feat: Add chest sorting support
fix: Fix crash when sorting empty inventory
docs: Update README with installation instructions
```

## ブランチ戦略

- `main`: 安定版・リリース用
- `develop`: 開発用・統合ブランチ
- `feature/*`: 新機能開発用（必要に応じて）
