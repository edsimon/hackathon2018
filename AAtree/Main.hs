{-
  to run:
  $ ghc -e main Main.hs < swahili-small.txt
  to compile and run:
  $ ghc -O Main.hs && ./Main < swahili-small.txt
-}
import AATree
--------------------------------------------------------------------------------


str :: String
str = "The quick brown fox jumps over the lazy dog"

main :: IO ()
main = do
  content   <- getContents
  let tree = foldr insert emptyTree (words content)
  putStrLn $ "Size: " ++ (show $ size tree)
  putStrLn $ "Height: " ++ (show $ height tree)
  putStrLn $ "Optimal height: " ++ (show $ optimalHeight tree)
  putStrLn $ "Height / Optimal height: " ++ (show (fromIntegral(height tree) / fromIntegral(optimalHeight tree)))
  putStrLn $ "checkTree: " ++ show (checkTree tree)
  putStrLn $ "First 20 words: " ++ unwords (take 20 (inorder tree))


  -- calculate and print statistics
  -- use fromIntegral/ceiling/logBase

optimalHeight :: AATree a -> Int
optimalHeight tree = ceiling (logBase 2 (fromIntegral((size tree)+1)) - 1)
