```markdown
## 今後対応していきたいこと

### マルチモジュール化したい

- `app`モジュールの追加
- `feature`モジュールを作成し配下にそれぞれ
  - `feature:taskList`
  - `feature:taskInput`
- `build-logic`モジュールを追加しコンベンションプラグインをマルチモジュール環境で適用
- `core`モジュールを作成し配下にそれぞれ
  - `core:database`
  - `core:model`

### テスト実装について

- `viewModel`と`Db`関係のUnitテストを実装したい
- 画面コンポーザブルのUIテストを実装したい
```
