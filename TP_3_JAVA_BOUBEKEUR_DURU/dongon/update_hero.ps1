Add-Type -AssemblyName System.Drawing

# Charger le nouveau sprite du héros
$heroPath = "C:\Users\melvy\.gemini\antigravity\brain\01a3962a-9a62-43b3-a95f-57d32e4d07c5\hero_sprite_improved_1766008040873.png"
$tilesheetPath = "c:\Users\melvy\Downloads\donjon project\dongon\img\tileSheet.png"

# Charger les images
$hero = [System.Drawing.Image]::FromFile($heroPath)
$tilesheet = [System.Drawing.Image]::FromFile($tilesheetPath)

# Créer un nouveau bitmap pour le tilesheet
$newTilesheet = New-Object System.Drawing.Bitmap($tilesheet.Width, $tilesheet.Height)
$graphics = [System.Drawing.Graphics]::FromImage($newTilesheet)

# Copier le tilesheet original
$graphics.DrawImage($tilesheet, 0, 0)

# Redimensionner le héros à 16x16 si nécessaire
$heroResized = New-Object System.Drawing.Bitmap(16, 16)
$heroGraphics = [System.Drawing.Graphics]::FromImage($heroResized)
$heroGraphics.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::NearestNeighbor
$heroGraphics.DrawImage($hero, 0, 0, 16, 16)

# Placer le héros aux coordonnées (5, 6) -> position (80, 96) pixels
$graphics.DrawImage($heroResized, 80, 96, 16, 16)

# Sauvegarder
$tilesheet.Dispose()
$hero.Dispose()
$heroGraphics.Dispose()
$heroResized.Dispose()

$newTilesheet.Save($tilesheetPath, [System.Drawing.Imaging.ImageFormat]::Png)
$graphics.Dispose()
$newTilesheet.Dispose()

Write-Host "Sprite du héros intégré avec succès au tilesheet!"
